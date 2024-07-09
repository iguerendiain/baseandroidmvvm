# NachoLab's Android MVVM base

### Basic Android project following MVVM with the following components:

- Retrofit API configured with easy to add interceptors anc cachè
- Meta Flipper with already configured SQLite, SharedPreferences and Network plugins
- Several Android tools for doing stuff like converting units, managing the virtual keyboard, handeling of navbar and statusbar, etc...
- BaseFragment and BaseScreenFragment to help with screens and dialogs creation.
- Hilt dependency injection already configured for all componentes on the app.
- MainRepository and MainViewModel already created and configured with DI
- Android X Navigation configured with an Activity and a Fragment
- Lorem Ipsum already added to `strings.xml`
- Data Binding already configured

### How to use?

However you like, just download and start coding. Beyond that, these are my suggestions:

1. Open the project in Android Studio
2. Change app's name in `settings.gradle.kts` -> `rootProject.name` and sync project with Gradle files `Ctrl+Alt+O` (Optionally reopen the project so the name of the window is updated)
3. Change app's name in `strings.xml`
4. Change app's name in `themes.xml` and `AndroidManifest.xml`
5. Change package name in module's `build.gradle.kts`
6. Refactor the name of the package dir `nacholab.androidbase`
7. Run the app, you should see the app's name centered in the screen

