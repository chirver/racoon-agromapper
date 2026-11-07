#!/usr/bin/env bash
# Compila el APK SIN Android Studio ni Gradle (útil en entornos sin acceso a Google Maven).
#   FLAVOR=parrot (por defecto) → «Racoon Mapper»: Bluegrass/Disco, ARSDK3. Id com.racoonair.mapper.
#   FLAVOR=anafi                → «Racoon Mapper ANAFI»: ANAFI, GroundSDK 1.8. Id com.racoonair.mapper.anafi.
# Requisitos (rutas por variables de entorno):
#   KOTLINC     -> kotlinc/bin/kotlinc (Kotlin 2.0.x)
#   ANDROID_JAR -> android.jar de API 31
#   DX_JAR      -> dx-30.x.jar (viene dentro de dex2jar/dex-tools)
#   STDLIB_JAR  -> kotlin-stdlib.jar
#   AAPT2       -> aapt2 (p. ej. el que trae apktool en prebuilt/linux/aapt2_64)
# Usa build-local/arsdk-3.14.dex (ARSDK3) y, en anafi, groundsdk-1.8-{a,b}.dex.
set -euo pipefail
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
SRC="$ROOT/app/src"
FLAVOR="${FLAVOR:-parrot}"
OUT="${OUT:-$ROOT/build-local/out}/$FLAVOR"
VERSION_CODE="${VERSION_CODE:-1}"
VERSION_NAME="${VERSION_NAME:-0.1}"
rm -rf "$OUT" && mkdir -p "$OUT/classes" "$OUT/dex" "$OUT/apk/lib" "$OUT/gen"

case "$FLAVOR" in
  parrot) PKG=com.racoonair.mapper; MANIFEST="$SRC/main/AndroidManifest.xml"; EXTRA_CP=""; EXTRA_PKGS="" ;;
  anafi)  PKG=com.racoonair.mapper.anafi; MANIFEST="$SRC/anafi/AndroidManifest.xml"; EXTRA_CP=":$HERE/groundsdk-1.8-api.jar"
          EXTRA_PKGS="--extra-packages com.parrot.drone.groundsdk:com.parrot.drone.groundsdk.arsdkengine:com.parrot.drone.groundsdk.sdkcore" ;;
  *) echo "FLAVOR desconocido: $FLAVOR"; exit 1 ;;
esac
echo "== Sabor: $FLAVOR ($PKG) =="

echo "[0/6] Recursos + manifest + clases R (aapt2)"
python3 - "$MANIFEST" "$OUT/AndroidManifest.xml" <<'PYEOF'
import sys
src = open(sys.argv[1]).read().replace('<manifest xmlns:android="http://schemas.android.com/apk/res/android">',
  '<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.racoonair.mapper">')
open(sys.argv[2], 'w').write(src)
PYEOF
RES_ZIPS=("$OUT/res-main.zip")
"$AAPT2" compile --dir "$SRC/main/res" -o "$OUT/res-main.zip"
if [ -d "$SRC/$FLAVOR/res" ]; then "$AAPT2" compile --dir "$SRC/$FLAVOR/res" -o "$OUT/res-$FLAVOR.zip"; RES_ZIPS+=("$OUT/res-$FLAVOR.zip"); fi
# El código vive en com.racoonair.mapper; el id de la app puede cambiar (ANAFI) para instalarse a la vez.
"$AAPT2" link -I "$ANDROID_JAR" --manifest "$OUT/AndroidManifest.xml" -o "$OUT/base.apk" \
  --min-sdk-version 26 --target-sdk-version 29 --version-code "$VERSION_CODE" --version-name "$VERSION_NAME" \
  --rename-manifest-package "$PKG" --java "$OUT/gen" $EXTRA_PKGS "${RES_ZIPS[@]}"
javac -nowarn --release 8 -d "$OUT/classes" $(find "$OUT/gen" -name '*.java')

echo "[1/6] Kotlin → .class"
"$KOTLINC" "$SRC/main/java" "$SRC/$FLAVOR/java" -cp "$ANDROID_JAR:$ROOT/app/libs/arsdk-3.14-classes.jar$EXTRA_CP:$OUT/classes" \
  -d "$OUT/classes" -jvm-target 1.8 -Xlambdas=class -Xsam-conversions=class -no-reflect -nowarn
(cd "$OUT/classes" && zip -qr ../app.jar .)

echo "[2/6] kotlin-stdlib limpio"
mkdir -p "$OUT/std" && (cd "$OUT/std" && unzip -qo "$STDLIB_JAR" -x 'META-INF/*' && find . -name module-info.class -delete && zip -qr ../stdlib.jar .)

echo "[3/6] .class → classes.dex (dx)"
java -Xmx3g -cp "$DX_JAR" com.android.dx.command.Main --dex --min-sdk-version=26 --multi-dex \
  --output="$OUT/dex" "$OUT/app.jar" "$OUT/stdlib.jar"
cp "$OUT/dex/classes.dex" "$OUT/apk/classes.dex"
DEXES=(classes.dex classes2.dex)
cp "$HERE/arsdk-3.14.dex" "$OUT/apk/classes2.dex"
if [ "$FLAVOR" = anafi ]; then
  cp "$HERE/groundsdk-1.8-a.dex" "$OUT/apk/classes3.dex"
  cp "$HERE/groundsdk-1.8-b.dex" "$OUT/apk/classes4.dex"
  DEXES+=(classes3.dex classes4.dex)
fi

echo "[5/6] dex + librerías nativas"
cp "$OUT/base.apk" "$OUT/unsigned.apk"
cp -r "$SRC/main/jniLibs/." "$OUT/apk/lib/"
[ -d "$SRC/$FLAVOR/jniLibs" ] && cp -r "$SRC/$FLAVOR/jniLibs/." "$OUT/apk/lib/"
cp "$ROOT/LICENSE-ARSDK.txt" "$OUT/apk/" 2>/dev/null || true
(cd "$OUT/apk" && zip -qr -X ../unsigned.apk "${DEXES[@]}" lib LICENSE-ARSDK.txt)

echo "[6/6] Firma (v1, clave de depuración)"
KS="$HERE/debug.keystore"
if [ ! -f "$KS" ]; then
  keytool -genkeypair -keystore "$KS" -storepass android -keypass android -alias racoon \
    -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Racoon Mapper Debug,O=Racoon Air,C=ES" >/dev/null
fi
NAME="racoon-mapper-$FLAVOR.apk"
cp "$OUT/unsigned.apk" "$OUT/$NAME"
jarsigner -keystore "$KS" -storepass android -keypass android -sigalg SHA256withRSA -digestalg SHA-256 \
  "$OUT/$NAME" racoon >/dev/null
echo "OK → $OUT/$NAME"
