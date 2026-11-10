# Racoon AgroMapper 0.13 (Bluegrass/Disco) and ANAFI 0.16 — clean third-party build

- **ANAFI:** GroundSDK 1.8.0 is now compiled from Parrot's official open-source code (`third_party/groundsdk`),
  with okio/okhttp/retrofit/gson compiled from their own source. No Google Play Services. The previous build used
  SDK classes taken from another app's package; they are gone.
- **Bluegrass/Disco:** the ARSDK3 Java dex now contains only the classes Parrot's SDK actually uses
  (com.parrot.* plus jmdns, Sanselan and LocalBroadcastManager, all Apache-2.0). Unused leftovers removed.
- Native libraries are unchanged (see NOTICE.md for where they come from).
- ⚠️ Not flown yet with these builds: test on the ground (connect, video, upload a plan) before flying.
