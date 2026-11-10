#!/usr/bin/env bash
# Rebuilds build-local/groundsdk-deps.jar from the libraries' own source code (no Maven needed):
#   okio 1.17.5, okhttp 3.12.13 (+ logging-interceptor), retrofit 2.5.0 (+ gson/scalars converters), gson 2.8.9
#   (all Apache-2.0), plus the small stand-ins in third_party/deps-patches (AndroidX annotations, a minimal
#   LocalBroadcastManager, JSR-305 annotations, a no-op ConscryptPlatform and the two version templates).
# Needs: git, javac (JDK 11+), ANDROID_JAR (API 31), STDLIB_JAR (kotlin-stdlib, used by retrofit's BuiltInConverters).
set -euo pipefail
HERE="$(cd "$(dirname "$0")" && pwd)"; ROOT="$(dirname "$HERE")"
W="${WORK:-$(mktemp -d)}"; cd "$W"
clone() { [ -d "$2" ] || git clone -q --depth 1 --branch "$3" "https://github.com/$1.git" "$2"; }
clone square/okio okio okio-parent-1.17.5
clone square/okhttp okhttp parent-3.12.13
clone square/retrofit retrofit parent-2.5.0
clone google/gson gson gson-parent-2.8.9
# Patches: Conscrypt is not bundled on Android builds of this app.
cp "$ROOT/third_party/deps-patches/okhttp3/internal/platform/ConscryptPlatform.java" okhttp/okhttp/src/main/java/okhttp3/internal/platform/
mkdir -p stubs && cp -r "$ROOT/third_party/deps-patches/." stubs/ && rm -rf stubs/okhttp3/internal/platform
sed 's/\${project.version}/2.8.9/' gson/gson/src/main/java-templates/com/google/gson/internal/GsonBuildConfig.java > stubs/com/google/gson/internal/GsonBuildConfig.java
sed 's/\${project.version}/3.12.13/' okhttp/okhttp/src/main/java-templates/okhttp3/internal/Version.java > stubs/okhttp3/internal/Version.java
find stubs okio/okio/src/main/java okhttp/okhttp/src/main/java okhttp/okhttp-logging-interceptor/src/main/java \
  retrofit/retrofit/src/main/java retrofit/retrofit-converters/gson/src/main/java retrofit/retrofit-converters/scalars/src/main/java \
  gson/gson/src/main/java -name '*.java' -not -name module-info.java > srcs.txt
rm -rf cls && mkdir cls
javac -nowarn -source 8 -target 8 -bootclasspath "$ANDROID_JAR" -cp "$STDLIB_JAR" -encoding UTF-8 -d cls @srcs.txt
(cd cls && rm -f "$HERE/groundsdk-deps.jar" && zip -qr "$HERE/groundsdk-deps.jar" .)
echo "OK → $HERE/groundsdk-deps.jar"
