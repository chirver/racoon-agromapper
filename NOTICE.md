# Third-party components / Componentes de terceros

Racoon AgroMapper's own source code is licensed under the GNU GPL v3.0. The APK also bundles the following
third-party software, each under its own licence:

| Component | Owner | Licence | Where |
|---|---|---|---|
| ARSDK3 3.14 (Java classes + native libraries) | Parrot SA | BSD-3-Clause ([LICENSE-ARSDK.txt](LICENSE-ARSDK.txt)) | `app/libs`, `app/src/main/jniLibs`, `build-local/arsdk-3.14.dex` |
| Helper libraries bundled inside that ARSDK build (jmdns, sanselan) | Apache Software Foundation and others | Apache-2.0 | inside the ARSDK dex |
| GroundSDK 1.8 (ANAFI flavour only) | Parrot Drones SAS | BSD-3-Clause | `app/src/anafi`, `build-local/groundsdk-*` |
| Kotlin standard library | JetBrains | Apache-2.0 | bundled at build time |

**About the Parrot SDK binaries:**
- Parrot used to publish the ARSDK on JCenter, which no longer exists.
- The ARSDK3 and GroundSDK binaries in this repository were extracted from publicly distributed Android packages that embed them. They are Parrot's open-source SDKs, whose BSD licence allows redistribution in binary form with the licence notice.
- No code from those packages' own applications is included. Their behaviour was only studied to reproduce the Parrot flight-plan protocol (MAVLink file layout and command sequence).
- Rebuilding the SDKs from Parrot's official sources (`github.com/Parrot-Developers`) is a welcome contribution.

## Map and data services used at run time

| Service | Owner | Terms |
|---|---|---|
| PNOA orthophoto (WMTS) | Instituto Geográfico Nacional (IGN) | CC BY 4.0 |
| SIGPAC parcels (query service and WMS) | FEGA | CC BY 4.0 |
| World Imagery (fallback outside Spain) | Esri | Esri terms of use, viewing only |
| Weather forecast | Open-Meteo | CC BY 4.0 |
| Planetary K-index | NOAA SWPC | Public domain |
