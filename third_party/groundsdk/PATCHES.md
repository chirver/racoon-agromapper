# Local changes to GroundSDK 1.8.0

Source: https://github.com/Parrot-Developers/groundsdk-android (commit "GroundSdk 1.8.0 release"), modules
`groundsdk`, `arsdkengine` and `sdkcore` (`src/main/java` and `src/main/res`), BSD-3-Clause (see LICENSE.md).
Command classes in `java/com/parrot/drone/sdkcore/arsdk/ArsdkFeature*.java` were generated with
`arsdk-xml/arsdkgen.py sdkcore/tools/arsdkgenjava.py` from https://github.com/Parrot-Developers/arsdk-xml
at commit 95eb04e ("GroundSdk 1.8.0 release").

Changes made for Racoon AgroMapper (built without Gradle, Maven or Google Play Services):

1. `internal/engine/system/SystemLocationCore.java`: Google Play Services fused location removed; GroundSDK uses
   its own fallback, the Android GPS provider.
2. `RequestBody.create(x, mediaType)` → `RequestBody.create(mediaType, x)` in 7 HTTP clients, and
   `loggingInterceptor.level(...)` → `setLevel(...)`: same calls in the okhttp 3.12 API (Java) instead of 4.x (Kotlin).
3. Added `com/parrot/drone/groundsdk/BuildConfig.java` and `com/parrot/drone/groundsdk/sdkcore/BuildConfig.java`,
   which Gradle would normally generate (`DEBUG = false`).
4. `hmd/Renderer.java` and `hmd/GsdkHmdView.java`: exceptions on the HMD GL thread (and while drawing the overlay)
   are caught and handed to a public static `GsdkHmdView.sErrorListener` instead of terminating the app.

App-level configuration overrides (no data sent to any server, no crash reports, no flight logs, no firmware or
ephemeris downloads) live in `app/src/anafi/res/values/gsdk_config.xml`.
