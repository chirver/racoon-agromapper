# Third-party components / Componentes de terceros

Racoon AgroMapper's own source code is licensed under the GNU GPL v3.0. The APKs also bundle the following
third-party software, each under its own licence.

## ANAFI app (`FLAVOR=anafi`)

| Component | Owner | Licence | How it gets into the APK |
|---|---|---|---|
| **GroundSDK 1.8.0** (groundsdk, arsdkengine, sdkcore: Java code and resources) | Parrot Drones SAS | BSD-3-Clause ([third_party/groundsdk/LICENSE.md](third_party/groundsdk/LICENSE.md)) | **Compiled from Parrot's official source** ([github.com/Parrot-Developers/groundsdk-android](https://github.com/Parrot-Developers/groundsdk-android), release 1.8.0), vendored in `third_party/groundsdk`. Command classes generated with Parrot's own generator from [arsdk-xml](https://github.com/Parrot-Developers/arsdk-xml) at the GroundSDK 1.8.0 commit. Local changes are listed in [third_party/groundsdk/PATCHES.md](third_party/groundsdk/PATCHES.md). |
| okio 1.17.5, okhttp 3.12.13 (+ logging-interceptor), retrofit 2.5.0 (+ gson and scalars converters) | Square, Inc. | Apache-2.0 | Compiled from their source tags into `build-local/groundsdk-deps.jar` by `build-local/build_groundsdk_deps.sh`. |
| gson 2.8.9 | Google | Apache-2.0 | Same jar, compiled from source. |
| Small stand-ins: AndroidX annotations, a minimal `LocalBroadcastManager`, JSR-305 annotations, no-op `ConscryptPlatform` | written for this project | GPL-3.0 (this project) / Apache-2.0 where derived | `third_party/deps-patches` |
| GroundSDK native library `libsdkcore.so` (+ `libc++_shared.so`) | Parrot Drones SAS (and the open-source libraries it links: arsdk-ng, pdraw, libmux, libpomp, ulog, libjpeg-turbo…) | see note below | `app/src/anafi/jniLibs` |

## Bluegrass / Disco app (`FLAVOR=parrot`)

| Component | Owner | Licence | How it gets into the APK |
|---|---|---|---|
| ARSDK3 3.14 (Java classes + native libraries) | Parrot SA | BSD-3-Clause ([LICENSE-ARSDK.txt](LICENSE-ARSDK.txt)) | `build-local/arsdk-3.14.dex`, `app/libs/arsdk-3.14-classes.jar` (compile only), `app/src/main/jniLibs` |
| Helper libraries used by that ARSDK build: jmdns, Apache Sanselan, AndroidX LocalBroadcastManager | Apache Software Foundation, jmdns authors, Google | Apache-2.0 | inside the ARSDK dex (class names shortened by the original build) |
| Kotlin standard library | JetBrains | Apache-2.0 | both apps, at build time |
| Lens profiles for goggles mode (`app/src/main/res/raw/hmd_models`, Parrot Cockpit Glasses 2 and other headsets) and the lens-correction method/shaders adapted in `GogglesView.kt` | Parrot Drones SAS | BSD-3-Clause | from the GroundSDK 1.8.0 demo app (`groundsdkdemo/src/main/res/raw/gsdkdemo_hmd_models`) and `groundsdk/hmd` |

## Where the binaries come from (honest version)

- Parrot used to publish ARSDK3 and GroundSDK binaries on JCenter, which no longer exists, and their native code is
  built with Parrot's own build system. We could not rebuild the **native** libraries here, so `libsdkcore.so` and the
  ARSDK3 `.so` files were taken from publicly distributed Android packages that embed Parrot's SDKs.
- The ARSDK3 **Java** dex was taken the same way and then **reduced to exactly the classes the Parrot SDK uses**
  (`com.parrot.*` plus the Apache libraries above). Everything else that came with it was removed.
- The GroundSDK **Java** code is no longer taken from any package: it is compiled from Parrot's public source.
- `libsdkcore.so` also contains a thermal-processing module (`libtproc`) that is not part of Parrot's public
  repositories. This app never calls it (no Java code for it is included), but the binary is shipped as it was built.
  Replacing these native libraries with ones built from Parrot's official sources is the main open task; help welcome.
- If any rights holder objects to a binary in this repository, open an issue and it will be removed.

## Map and data services used at run time

| Service | Owner | Terms |
|---|---|---|
| PNOA orthophoto (WMTS) | Instituto Geográfico Nacional (IGN) | CC BY 4.0 |
| SIGPAC parcels (query service and WMS) | FEGA | CC BY 4.0 |
| World Imagery (fallback outside Spain) | Esri | Esri terms of use, viewing only |
| Weather forecast | Open-Meteo | CC BY 4.0 |
| Planetary K-index | NOAA SWPC | Public domain |
