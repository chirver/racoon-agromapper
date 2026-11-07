package com.racoonair.mapper

import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale
import kotlin.math.abs

/**
 * Recintos SIGPAC (FEGA, «SIGPAC en la nube», datos CC BY 4.0).
 * Consulta por punto: https://sigpac-hubcloud.es/servicioconsultassigpac/query/recinfobypoint/4258/{lon}/{lat}.json
 * (ETRS89 = EPSG:4258, a efectos prácticos igual que el GPS). Devuelve atributos + contorno en WKT.
 */
object Sigpac {

    data class Recinto(
        val provincia: Int, val municipio: Int, val agregado: Int, val zona: Int,
        val poligono: Int, val parcela: Int, val recinto: Int,
        val superficieHa: Double, val pendiente: Double?, val uso: String,
        /** Anillo exterior del polígono que contiene el punto tocado (lat/lon). */
        val contorno: List<LatLon>,
        val huecos: Int
    ) {
        val codigo get() = "$provincia:$municipio:$agregado:$zona:$poligono:$parcela:$recinto"
        val nombre get() = "Pol $poligono · Parc $parcela · Rec $recinto"
        fun descripcion(): String = buildString {
            append("$nombre  (${codigo})\n")
            append("Uso: ${USOS[uso] ?: uso}  ·  ${"%.2f".format(Locale.ROOT, superficieHa)} ha")
            pendiente?.let { append("  ·  pendiente media ${"%.1f".format(Locale.ROOT, it)} %") }
        }
    }

    val USOS = mapOf(
        "TA" to "tierra arable", "VI" to "viñedo", "OV" to "olivar", "PS" to "pastizal", "PR" to "pasto arbustivo",
        "PA" to "pasto con arbolado", "FO" to "forestal", "FY" to "frutales", "FS" to "frutos secos", "CI" to "cítricos",
        "TH" to "huerta", "IV" to "invernadero", "VO" to "viñedo-olivar", "VF" to "viñedo-frutal", "OF" to "olivar-frutal",
        "FL" to "frutos secos y olivar", "FV" to "frutos secos y viñedo", "CA" to "viales", "ED" to "edificación",
        "IM" to "improductivo", "AG" to "agua", "ZU" to "zona urbana", "ZC" to "zona concentrada", "MT" to "matorral",
        "CF" to "cítricos-frutal", "CS" to "cítricos-frutos secos", "CV" to "cítricos-viñedo", "OC" to "olivar-cítricos"
    )

    fun url(p: LatLon) = String.format(Locale.ROOT,
        "https://sigpac-hubcloud.es/servicioconsultassigpac/query/recinfobypoint/4258/%.7f/%.7f.json", p.lon, p.lat)

    /** Bloqueante (llamar fuera del hilo principal). Lista vacía = no hay recinto en ese punto. */
    fun query(p: LatLon): List<Recinto> = parse(Net.getText(url(p)), p)

    fun parse(json: String, tap: LatLon): List<Recinto> {
        val t = json.trim()
        if (t.isEmpty()) return emptyList()
        val arr = when {
            t.startsWith("[") -> JSONArray(t)
            t.startsWith("{") -> {
                val o = JSONObject(t)
                // Por si algún día responde en GeoJSON: FeatureCollection → properties.
                if (o.has("features")) JSONArray().also { a ->
                    val f = o.getJSONArray("features"); for (i in 0 until f.length()) a.put(f.getJSONObject(i).optJSONObject("properties") ?: JSONObject())
                } else JSONArray().put(o)
            }
            else -> throw IllegalArgumentException("respuesta SIGPAC no reconocida")
        }
        val out = ArrayList<Recinto>()
        for (i in 0 until arr.length()) {
            val o = arr.optJSONObject(i) ?: continue
            try {
            val wkt = o.optString("wkt", "")
            if (wkt.isEmpty()) continue
            val srid = o.optInt("srid", 4258)
            if (srid != 4258 && srid != 4326) continue // pedimos 4258; cualquier otra cosa (UTM…) no se interpreta
            val polys = parseWkt(wkt).filter { it.isNotEmpty() && it[0].size >= 3 }
            if (polys.isEmpty()) continue
            val oriented = polys.map { poly -> poly.map { ring -> orient(ring, tap) } }
            // El polígono que contiene el punto tocado; si ninguno, el mayor.
            val chosen = oriented.firstOrNull { contains(it[0], tap) } ?: oriented.maxByOrNull { abs(area(it[0])) }!!
            val ring = chosen[0].let { if (it.size > 1 && it.first() == it.last()) it.dropLast(1) else it }
            if (ring.size < 3 || ring.any { abs(it.lat) > 90 || abs(it.lon) > 180 }) continue
            out.add(Recinto(
                o.optInt("provincia"), o.optInt("municipio"), o.optInt("agregado"), o.optInt("zona"),
                o.optInt("poligono"), o.optInt("parcela"), o.optInt("recinto"),
                o.optDouble("superficie", 0.0).let { if (it.isNaN()) 0.0 else it },
                if (o.isNull("pendiente_media")) null else o.optDouble("pendiente_media").takeIf { !it.isNaN() },
                o.optString("uso_sigpac", "?"), ring, chosen.size - 1
            ))
            } catch (t: Throwable) { Crumbs.write("sigpac: recinto ilegible ${t.javaClass.simpleName}: ${t.message}") }
        }
        return out
    }

    // ---------------------------------------------------------------- WKT

    /** POLYGON / MULTIPOLYGON (opcionalmente con «SRID=n;») → polígonos → anillos → puntos (x=lon, y=lat). */
    fun parseWkt(wkt: String): List<List<List<DoubleArray>>> {
        var s = wkt.trim()
        if (s.startsWith("SRID=", true)) s = s.substringAfter(';')
        if (s.indexOf('(') < 0) return emptyList() // «POLYGON EMPTY»
        val type = s.substringBefore('(').trim().uppercase(Locale.ROOT).replace(Regex("\\s*(ZM|Z|M)$"), "")
        val body = s.substring(s.indexOf('('))
        val tree = Nested(body).parse()
        return when (type) {
            "POLYGON" -> listOf(rings(tree))
            "MULTIPOLYGON" -> tree.children.map { rings(it) }
            else -> emptyList()
        }
    }

    private class Node(val children: MutableList<Node> = ArrayList(), var text: String = "")

    private class Nested(val s: String) {
        var i = 0
        fun parse(): Node {
            // s[i] == '('
            i++
            val n = Node()
            val sb = StringBuilder()
            while (i < s.length) {
                val c = s[i]
                when (c) {
                    '(' -> n.children.add(parse())
                    ')' -> { i++; n.text = sb.toString(); return n }
                    else -> { sb.append(c); i++ }
                }
            }
            n.text = sb.toString(); return n
        }
    }

    private fun rings(poly: Node): List<List<DoubleArray>> = poly.children.map { ring ->
        ring.text.split(',').mapNotNull { pt ->
            val v = pt.trim().split(Regex("\\s+")).mapNotNull { it.toDoubleOrNull() }
            if (v.size >= 2) doubleArrayOf(v[0], v[1]) else null
        }
    }

    /** Pasa a LatLon; si el orden de ejes viene al revés (lat lon), se corrige comparando con el punto tocado. */
    private fun orient(ring: List<DoubleArray>, tap: LatLon): List<LatLon> {
        if (ring.isEmpty()) return emptyList()
        val f = ring[0]
        val dNormal = abs(f[0] - tap.lon) + abs(f[1] - tap.lat)
        val dSwapped = abs(f[1] - tap.lon) + abs(f[0] - tap.lat)
        val swap = dSwapped < dNormal
        return ring.map { if (swap) LatLon(it[0], it[1]) else LatLon(it[1], it[0]) }
    }

    fun contains(ring: List<LatLon>, p: LatLon): Boolean {
        var inside = false
        var j = ring.size - 1
        for (i in ring.indices) {
            val a = ring[i]; val b = ring[j]
            if ((a.lat > p.lat) != (b.lat > p.lat) &&
                p.lon < (b.lon - a.lon) * (p.lat - a.lat) / (b.lat - a.lat) + a.lon) inside = !inside
            j = i
        }
        return inside
    }

    private fun area(r: List<LatLon>): Double {
        var s = 0.0
        for (i in r.indices) { val a = r[i]; val b = r[(i + 1) % r.size]; s += a.lon * b.lat - b.lon * a.lat }
        return s / 2
    }

    // ---------------------------------------------------------------- simplificación

    /**
     * Douglas–Peucker en metros: los recintos traen cientos de vértices; para planificar bastan
     * unos pocos (tolerancia [tolM]) y el planificador queda manejable.
     */
    data class Simplified(val points: List<LatLon>, val tolM: Double)

    /** Tolerancia como mucho 4 m (el plan no debe salirse de la parcela más que eso); sin cruces. */
    fun simplify(ring: List<LatLon>, tolM: Double = 1.5, maxPts: Int = 150): Simplified {
        if (ring.size <= 4) return Simplified(ring, 0.0)
        var tol = tolM
        var r = dp(ring, tol)
        while (r.size > maxPts && tol < 4.0) { tol = minOf(4.0, tol * 1.5); r = dp(ring, tol) }
        // Si la simplificación cruza bordes, se baja la tolerancia; en último caso, el contorno original.
        while (r.size >= 3 && selfIntersects(r) && tol > 0.3) { tol /= 2; r = dp(ring, tol) }
        if (r.size < 3 || selfIntersects(r)) return Simplified(ring, 0.0)
        return Simplified(r, tol)
    }

    fun selfIntersects(r: List<LatLon>): Boolean {
        val n = r.size
        fun ccw(a: LatLon, b: LatLon, c: LatLon) = (c.lat - a.lat) * (b.lon - a.lon) - (b.lat - a.lat) * (c.lon - a.lon)
        for (i in 0 until n) {
            val a = r[i]; val b = r[(i + 1) % n]
            for (j in i + 2 until n) {
                if (i == 0 && j == n - 1) continue // aristas contiguas (cierre)
                val c = r[j]; val d = r[(j + 1) % n]
                val d1 = ccw(c, d, a); val d2 = ccw(c, d, b); val d3 = ccw(a, b, c); val d4 = ccw(a, b, d)
                if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) return true
            }
        }
        return false
    }

    private fun dp(ring: List<LatLon>, tol: Double): List<LatLon> {
        val o = ring[0]
        val k = Math.cos(Math.toRadians(o.lat))
        val xy = ring.map { doubleArrayOf((it.lon - o.lon) * 111320.0 * k, (it.lat - o.lat) * 110540.0) }
        val keep = BooleanArray(ring.size)
        // Anillo cerrado: partir por el punto más lejano al primero.
        var far = 0; var best = -1.0
        for (i in xy.indices) { val d = Math.hypot(xy[i][0], xy[i][1]); if (d > best) { best = d; far = i } }
        keep[0] = true; keep[far] = true
        fun rec(a: Int, b: Int) {
            if (b <= a + 1) return
            var idx = -1; var dmax = 0.0
            for (i in a + 1 until b) { val d = segDist(xy[i], xy[a], xy[b]); if (d > dmax) { dmax = d; idx = i } }
            if (dmax > tol && idx > 0) { keep[idx] = true; rec(a, idx); rec(idx, b) }
        }
        rec(0, far)
        // Segunda mitad: de far al final y vuelta al 0 (se añade el 0 como cierre virtual).
        val ext = xy + listOf(xy[0])
        fun rec2(a: Int, b: Int) {
            if (b <= a + 1) return
            var idx = -1; var dmax = 0.0
            for (i in a + 1 until b) { val d = segDist(ext[i], ext[a], ext[b]); if (d > dmax) { dmax = d; idx = i } }
            if (dmax > tol && idx > 0) { if (idx < ring.size) keep[idx] = true; rec2(a, idx); rec2(idx, b) }
        }
        rec2(far, ring.size)
        return ring.filterIndexed { i, _ -> keep[i] }
    }

    private fun segDist(p: DoubleArray, a: DoubleArray, b: DoubleArray): Double {
        val dx = b[0] - a[0]; val dy = b[1] - a[1]
        val l2 = dx * dx + dy * dy
        if (l2 == 0.0) return Math.hypot(p[0] - a[0], p[1] - a[1])
        val t = (((p[0] - a[0]) * dx + (p[1] - a[1]) * dy) / l2).coerceIn(0.0, 1.0)
        return Math.hypot(p[0] - (a[0] + t * dx), p[1] - (a[1] + t * dy))
    }

    // ---------------------------------------------------------------- capa WMS de recintos

    /** Tesela 256×256 de la capa de recintos (PNG transparente) en Web Mercator. */
    fun wmsTileUrl(z: Int, x: Int, y: Int): String {
        val o = 20037508.342789244
        val size = 2 * o / (1 shl z)
        val minx = -o + x * size; val maxx = minx + size
        val maxy = o - y * size; val miny = maxy - size
        return String.format(Locale.ROOT,
            "https://sigpac-hubcloud.es/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=AU.Sigpac:recinto&STYLES=" +
                "&SRS=EPSG:3857&BBOX=%.3f,%.3f,%.3f,%.3f&WIDTH=256&HEIGHT=256&FORMAT=image/png&TRANSPARENT=TRUE",
            minx, miny, maxx, maxy)
    }
}
