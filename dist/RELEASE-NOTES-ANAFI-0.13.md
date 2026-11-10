# Racoon AgroMapper ANAFI 0.13 (experimental)

Separate app for **Parrot ANAFI Thermal / ANAFI 4K** (SkyController 3). Installs alongside the Bluegrass/Disco app.

## New: 3D photogrammetry missions
- **Orbit** around an object (statue, tower, building): 1–3 rings, camera aimed at the object's centre.
- **Oblique double grid**: two crossed grids with the gimbal tilted (−60° by default) for blocks and roofs.
- **Façade**: horizontal rows from bottom to top in front of a wall, camera level.
- **Classic nadir grid** as before.
- Vertical climb over the take-off point before any transit; return at a safe altitude above the object.
- "Height difference" field for sites where the take-off is not at the object's base.
- Resume after battery swap is disabled for 3D missions.

⚠️ Not flown yet. The ANAFI has no obstacle avoidance: check trees, cables and lamp posts, keep line of sight and be ready to take control.

---
*App aparte para ANAFI Thermal / 4K con misiones 3D (órbita, doble cuadrícula oblicua, fachada y cuadrícula cenital). Experimental, aún sin volar.*

## 0.13.1
- Fix: «Conectar» no hacía nada. GroundSDK no arrancaba porque faltaban recursos del motor ARSDK (efemérides GPS). Los errores de conexión ahora se muestran en pantalla.

## 0.13.2
- Fix: `NoClassDefFoundError` al conectar. Faltaban clases auxiliares de las que depende GroundSDK (lambdas, protobuf, red…); van ahora en un tercer dex.

## 0.13.3
- Fix: GroundSDK pedía la declaración de Google Play services en el manifiesto. Se pide el permiso de ubicación al conectar.

## 0.13.4
- Primer arranque con conexión real (SkyController 3).
- Vídeo en directo con el visor de GroundSDK y botón RGB / térmica (ANAFI Thermal, paleta hierro).

## 0.13.5
- RGB / térmica cambia de cámara (modo térmico STANDARD / DISABLED, como FreeFlight 6) y reengancha el vídeo.

## 0.13.6
- Térmica en vídeo con el modo EMBEDDED (el dron colorea la térmica en el vídeo); STANDARD necesita un procesado en el móvil que GroundSDK no incluye.

## 0.14 — térmica de trabajo
- Paletas: absoluta (colores = °C fijos, lectura de máx y centro en pantalla), búsqueda (solo lo que supera el umbral, en rojo) y relativa.
- Detector de puntos calientes para búsqueda y rescate: pita, guarda la imagen y marca la posición en el mapa y en el KML.
- Mosaico térmico: paleta fija durante la misión y calibración del sensor en cada giro (nunca a mitad de pasada); aviso en el planificador si el solape o la velocidad no valen para térmica.
- Reanudar tras cambio de batería verificado para cuadrículas ANAFI.

## 0.14.1
- Detector: un objeto cercano que ocupa mucha imagen ya avisa (antes se descartaba como «suelo caliente»); diagnóstico de la lectura en el registro.
- Aviso por diferencia con el entorno (placas solares, búsqueda nocturna).
- Posición del punto caliente proyectada al suelo con la inclinación del gimbal, el rumbo y la altura.
- Inspección: en misión, al ver un punto caliente para, hace foto con la cámara del dron y sigue.

## 0.14.2
- Lista de puntos calientes (🔥 en la pantalla de vuelo): ver en el mapa, ir con Google Maps, compartir coordenadas, ver foto; exportar CSV o KML.
- Los puntos se guardan al momento y sobreviven a cerrar la app.
- Fotos de los puntos en la galería (Imágenes/RacoonAgroMapper) con la posición del punto en el EXIF.

## 0.14.3
- Fix: la app se cerraba al desconectar (GroundSDK paraba su motor mientras desconectaba el mando). La sesión se mantiene viva.
- Diagnóstico térmico: el registro muestra el modo y los colores que pinta el dron.
