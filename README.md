# Racoon AgroMapper

**Mapping flight planner for Parrot Bluegrass and Parrot Disco with the Sequoia multispectral camera — a free, open replacement for the discontinued Pix4Dcapture.**
*Planificador de vuelos de mapeo para Parrot Bluegrass y Parrot Disco con la cámara multiespectral Sequoia: un sustituto libre del desaparecido Pix4Dcapture.*

> ⚠️ **Unofficial app.** Not affiliated with, endorsed by or supported by Parrot SA or Pix4D SA. "Parrot", "Bluegrass", "Disco", "Sequoia", "ANAFI" and "Pix4D" are trademarks of their respective owners and are used here only to describe compatibility.
> **App no oficial**, sin relación con Parrot ni con Pix4D.

> ⚠️ **Use at your own risk.** This software commands real aircraft. It is provided WITHOUT ANY WARRANTY (see the GPL-3.0 licence). Always fly within the law, keep line of sight, and be ready to take manual control with your SkyController.
> **Uso bajo tu responsabilidad.** Esta app manda aviones de verdad y no tiene ninguna garantía. Vuela siempre dentro de la ley, con el dron a la vista y listo para tomar el control con el SkyController.

[![Donate · Invítame a un café](https://img.shields.io/badge/PayPal-Inv%C3%ADtame%20a%20un%20caf%C3%A9-0070ba?logo=paypal)](https://www.paypal.me/JMorenoChirveches)

🇬🇧 [English](#english) · 🇪🇸 [Español](#español) · Languages in the app: Español, English, Français, Deutsch, Italiano ([help translating](TRANSLATING.md))

---

## English

### What it does
- **Grid mapping missions** drawn on the map, or taken straight from a **SIGPAC** parcel (Spain), with Sequoia photo spacing calculated for your altitude and overlaps.
- **Bluegrass (multirotor)**: upload and fly over the **SkyController 2 (USB)** or WiFi with the same command sequence Pix4Dcapture used. Includes pause/resume, resume after a battery swap, return home, landing and emergency stop.
- **Disco (fixed wing)**:
  - Passes ordered so turns are never tighter than the aircraft can fly.
  - Hand launch.
  - **Planned landing**: landing point, final heading, approach corridor drawn on the orthophoto, and a suggested direction into the wind.
- **Sequoia without the drone** (Disco FPV): the app joins the Sequoia's WiFi, sets distance or interval capture from the plan and arms it. No web page involved.
- **Live video** from the drone camera, with an OSD (altitude, speed, battery, distance home). It switches with the map.
- **Safety**:
  - Automatic return home on low battery (configurable).
  - Alarms and pre-flight checklist.
  - A **weather and Kp traffic light** per aircraft (Open-Meteo + NOAA), with wind evaluated at flight altitude.
- **Offline maps**: Spain's PNOA orthophoto (IGN, CC BY 4.0) can be downloaded for fields without coverage.
- **ANAFI 3D photogrammetry** (ANAFI build): orbit around an object (statues, towers, buildings) with the camera aimed at its centre, oblique double grid for blocks and roofs, vertical façade grid, and the classic nadir grid.
- Flight log (CSV), KML export and diagnostics.

### Supported hardware
| Aircraft | Link | Status |
|---|---|---|
| Parrot Bluegrass + Sequoia | SkyController 2 (USB) / WiFi | ✅ flown |
| Parrot Disco FPV / Disco-Pro AG + Sequoia | SkyController 2 (USB) / WiFi | 🧪 ground-tested features; fixed-wing missions not flown yet |
| Parrot ANAFI Thermal / ANAFI 4K | SkyController 3 | 🧪 separate APK with 3D missions, not flown yet |

### Android compatibility
| | |
|---|---|
| **Minimum** | Android 8.0 (API 26) |
| **Tested** | Android 12 (Xiaomi Redmi Note 9S) |
| **Should work** | Android 8 – 15. Built for API 29, which current Android versions still install. |
| **CPU** | ARM 64-bit (arm64-v8a) or 32-bit (armeabi-v7a), i.e. practically every phone. Not x86 emulators. |
| **SkyController 2 by USB** | The phone must support USB host/accessory (OTG). Most phones do. |
| **Sequoia over WiFi (Disco)** | Automatic connection needs **Android 10+**. On Android 8–9, join the Sequoia WiFi by hand first. |
| **Maps, SIGPAC, weather** | Internet needed (mobile data works while connected to the drone). Downloaded maps work offline. |

Android may warn that the app is "unrecognised" (Play Protect) because it is not from the Play Store. Choose *Install anyway*.

### Install
**[⬇ Download RacoonAgroMapper-0.12.1.apk](https://github.com/chirver/racoon-agromapper/raw/main/dist/RacoonAgroMapper-0.12.1.apk)**, open it on the phone and allow installing from unknown sources. Older and newer builds: [`dist/`](dist) and [Releases](../../releases).
**ANAFI Thermal / 4K (3D missions, experimental):** [⬇ RacoonAgroMapper-ANAFI-0.13.3.apk](https://github.com/chirver/racoon-agromapper/raw/main/dist/RacoonAgroMapper-ANAFI-0.13.3.apk). It installs alongside the Bluegrass/Disco app.

### Build
No Android Studio or Gradle needed: `build-local/build_apk.sh` builds the APK with `aapt2`, `kotlinc` and `dx` (see the header of the script for the required paths). Flavours: `FLAVOR=parrot` (Bluegrass/Disco) and `FLAVOR=anafi`.

### Licence
Racoon AgroMapper is free software under the **GNU GPL v3.0** ([LICENSE](LICENSE)). Third-party components and their licences are listed in [NOTICE.md](NOTICE.md).

### Support the project
If the app saves your Bluegrass or Disco from the drawer, you can buy me a coffee: **[☕ PayPal](https://www.paypal.me/JMorenoChirveches)**

---

## Español

### Qué hace
- **Misiones de mapeo en cuadrícula**: se dibujan en el mapa o se toman directamente de un **recinto SIGPAC**. La distancia entre fotos de la Sequoia se calcula según tu altura y tus solapes.
- **Bluegrass (multirrotor)**: subir y volar por el **SkyController 2 (USB)** o por WiFi, con la misma secuencia de órdenes que Pix4Dcapture. Incluye pausa, reanudar tras cambiar la batería, vuelta a casa, aterrizaje y corte de emergencia.
- **Disco (ala fija)**:
  - Pasadas ordenadas para que los giros nunca sean más cerrados de lo que puede el avión.
  - Lanzamiento a mano.
  - **Aterrizaje planificado**: punto de toma, rumbo de la final, pasillo de aproximación dibujado sobre la ortofoto y dirección sugerida de cara al viento.
- **Sequoia sin el dron** (Disco FPV): la app se une a la WiFi de la Sequoia, programa el disparo por distancia o por tiempo según el plan y la deja armada. Sin pasar por su web.
- **Vídeo en directo** de la cámara del dron, con datos encima (altura, velocidad, batería, distancia a casa). Se alterna con el mapa.
- **Seguridad**:
  - Vuelta a casa automática por batería (configurable).
  - Alarmas y lista de comprobación antes de volar.
  - **Semáforo de meteo y Kp** según el dron (Open-Meteo + NOAA), con el viento a la altura de vuelo.
- **Mapas sin conexión**: ortofoto PNOA del IGN (CC BY 4.0), descargable para el campo sin cobertura.
- **Fotogrametría 3D con ANAFI** (APK de ANAFI): órbita alrededor de un objeto (estatuas, torres, edificios) con la cámara apuntando a su centro, doble cuadrícula oblicua para manzanas y tejados, rejilla vertical de fachada y la cuadrícula cenital de siempre.
- Registro de vuelo (CSV), exportación KML y diagnóstico.

### Compatibilidad con Android
- **Mínimo:** Android 8.0. **Probado:** Android 12 (Xiaomi Redmi Note 9S). Debería funcionar de Android 8 a 15.
- **Procesador:** ARM de 64 o 32 bits, es decir, prácticamente todos los móviles. No funciona en emuladores x86.
- **SkyController 2 por USB:** el móvil tiene que admitir USB OTG/accesorio, como hacen la mayoría.
- **Sequoia por WiFi (Disco):** la conexión automática necesita **Android 10 o superior**. En Android 8 y 9 hay que conectarse a mano a su WiFi antes.
- **Mapas, SIGPAC y meteo:** necesitan internet; los datos móviles sirven aunque estés conectado al dron. Los mapas descargados funcionan sin conexión.
- Android puede avisar de «app no reconocida» (Play Protect) porque no viene de la Play Store: pulsa *Instalar de todas formas*.

### Instalar
**[⬇ Descargar RacoonAgroMapper-0.12.1.apk](https://github.com/chirver/racoon-agromapper/raw/main/dist/RacoonAgroMapper-0.12.1.apk)**, ábrelo en el móvil y permite instalar apps de origen desconocido.
**ANAFI Thermal / 4K (misiones 3D, experimental):** [⬇ RacoonAgroMapper-ANAFI-0.13.3.apk](https://github.com/chirver/racoon-agromapper/raw/main/dist/RacoonAgroMapper-ANAFI-0.13.3.apk). Se instala junto a la app de Bluegrass/Disco.

### Licencia
Software libre bajo **GNU GPL v3.0** ([LICENSE](LICENSE)). Los componentes de terceros y sus licencias están en [NOTICE.md](NOTICE.md).

### Apoya el proyecto
Si la app rescata tu Bluegrass o tu Disco del cajón, puedes invitarme a un café: **[☕ PayPal](https://www.paypal.me/JMorenoChirveches)**

---
Made by [Racoon Air](https://racoon-air.com) · Hecho a cuatro manos con Claude.
