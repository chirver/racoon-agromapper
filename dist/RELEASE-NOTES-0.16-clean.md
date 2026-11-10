# Racoon AgroMapper 0.13 (Bluegrass/Disco) and ANAFI 0.16 — clean third-party build

- **ANAFI:** GroundSDK 1.8.0 is now compiled from Parrot's official open-source code (`third_party/groundsdk`),
  with okio/okhttp/retrofit/gson compiled from their own source. No Google Play Services. The previous build used
  SDK classes taken from another app's package; they are gone.
- **Bluegrass/Disco:** the ARSDK3 Java dex now contains only the classes Parrot's SDK actually uses
  (com.parrot.* plus jmdns, Sanselan and LocalBroadcastManager, all Apache-2.0). Unused leftovers removed.
- Native libraries are unchanged (see NOTICE.md for where they come from).
- ⚠️ Not flown yet with these builds: test on the ground (connect, video, upload a plan) before flying.

## ANAFI 0.16.1
- Fix: NoSuchMethodError LambdaMetafactory al conectar. El dex se genera con D8, que traduce las lambdas de Java 8 de GroundSDK (dx no lo hacía).

## Bluegrass/Disco 0.13.1
- Sequoia: cambiar de «por tiempo» a «por distancia» más robusto (espera a que termine de parar, modo y parámetro en la misma petición, reintentos con la configuración completa) y explica la causa si aun así lo rechaza.

## ANAFI 0.16.2 · Bluegrass/Disco 0.13.2
- Vídeo a pantalla completa (botón ⛶): telemetría y avisos encima; rumbo y, en la Disco, distancia y rumbo al punto de toma. Para aterrizar a mano en FPV y para búsqueda/inspección. Atrás o ✕ para salir.

## ANAFI 0.16.3 · Bluegrass/Disco 0.13.3
- Modo gafas (👓 en pantalla completa): pantalla partida con corrección de lente para Parrot Cockpitglasses (perfil oficial Cockpit Glasses 2), con telemetría y avisos dentro de la imagen. ANAFI con el visor de GroundSDK; Bluegrass/Disco con un visor propio adaptado del de GroundSDK. Se sale tocando la pantalla o con atrás.
