# Translating Racoon AgroMapper / Traducir la app

The app is written in Spanish. Each Spanish text is its own key, and the translations live in
`app/src/main/assets/i18n/<language>.json`:

```json
{
  "Batería al %1$s %%": "Battery at %1$s %%",
  "Volver a casa": "Return home"
}
```

**Rules:**
- Keep the **placeholders** exactly as they are: `%1$s`, `%2$s`, and so on. You may reorder them if your language needs it.
- Keep `%%`, which is a literal percent sign.
- Keep the line breaks (`\n`), emojis and symbols such as ✔ ✘ ⚠ ⛔.
- Any missing entry falls back to English, and then to Spanish. A partial translation is fine.
- Drone safety texts (alarms, return home, emergency) must stay **short and unambiguous**.

**To add a language:**
1. Copy `en.json` to `xx.json`, where `xx` is the ISO 639-1 code (`pt`, `nl`, `pl`…).
2. Translate the values. Do not change the keys.
3. Add the language to `I18n.LANGS` in `app/src/main/java/com/racoonair/mapper/I18n.kt`.
4. Open a pull request.

The included French, German and Italian translations were machine-assisted. Corrections from native speakers are very welcome.

---

*Para corregir o añadir un idioma, edita o copia uno de los ficheros de `app/src/main/assets/i18n/`. Las claves son el texto en español; respeta los marcadores `%1$s` y `%%`.*
