# Flag Game

An educational Android app built with Jetpack Compose and Kotlin, designed to help users learn about countries and their flags through interactive activities.

## Features

- **Country List Utility:** Reads and displays a list of countries from a local JSON file.
- **Guessing Games:** Interactive screens to guess countries and their flags, making learning fun.
- **Jetpack Compose UI:** Modern, declarative UI using Compose.
- **Navigation:** Simple navigation architecture.
- **Kotlin-first:** Entirely written in Kotlin, following official code style.
- **AndroidX & Material Design:** Uses the latest AndroidX libraries and Material components.

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Giraffe or newer recommended)
- Android SDK 34+
- JDK 8+

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/GameApp.git
   cd GameApp
   ```

2. **Open in Android Studio:**
   - Open the project folder in Android Studio.

3. **Build the project:**
   - Let Gradle sync and download dependencies.
   - Build the project using the "Build" menu or by pressing `Ctrl+F9`.

4. **Run the app:**
   - Connect an Android device or start an emulator.
   - Click "Run" or use `Shift+F10`.

### Project Structure

```
app/
  ├── src/
  │   ├── main/
  │   │   ├── java/com/example/gameapp/
  │   │   │   ├── MainActivity.kt
  │   │   │   └── CountryUtil.kt
  │   │   └── res/
  │   │       ├── raw/countries.json
  │   │       └── xml/
  │   │           ├── backup_rules.xml
  │   │           └── data_extraction_rules.xml
  ├── build.gradle.kts
  └── ...
```

- **MainActivity.kt:** App entry point, sets up navigation and reads country data.
- **CountryUtil.kt:** Utility for reading and displaying countries from JSON.
- **countries.json:** List of country codes and names.

### How It Works

- On launch, the app reads `countries.json` from the `res/raw` directory.
- The country list is parsed and displayed in a scrollable Compose `LazyColumn`.
- The UI is fully declarative and leverages Compose best practices.

## Customization

- To update the country list, edit `app/src/main/res/raw/countries.json`.
- UI and logic can be extended in the `CountryUtil.kt` and `MainActivity.kt` files.

## Contributing

Contributions are welcome! Please open issues or submit pull requests. This project is intended for educational purposes, so feel free to use it as a learning resource or to suggest improvements.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

git ## Acknowledgements

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin](https://kotlinlang.org/)
- [AndroidX](https://developer.android.com/jetpack/androidx)

---

*This project is for educational use.* 