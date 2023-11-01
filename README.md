

## Application

The News App is a simple application that presents information about various news across the countries. This data is dynamically fetched from newsapi.org .

The app has a few screens located in multiple feature modules:

- Home list screen - displays list of news
- News detail screen - display information about the selected news
- Search screen - empty (WiP)
- Favourites screen - empty (WiP)
  <br/><br/>

<p>
  <img src="config/assets/news_app.gif" width="250" />
</p>

## Tech-Stack

This project takes advantage of best practices and many popular libraries and tools in the Android ecosystem. Most of
the libraries are in the stable version unless there is a good reason to use non-stable dependency.

* Tech-stack
  * [100% Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including views
    + [Kotlin Symbol Processing](https://kotlinlang.org/docs/ksp-overview.html) - enable compiler plugins
    + [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
  * [Retrofit](https://square.github.io/retrofit/) - networking
  * [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when
      lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related
      data in a lifecycle-aware way
    * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
  * [Koin](https://insert-koin.io/) - dependency injection (dependency retrieval)
  * [Coil](https://github.com/coil-kt/coil) - image loading library
  * [Lottie](http://airbnb.io/lottie) - animation library
* Modern Architecture
  * [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  * Single activity architecture
    using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
  * MVVM + MVI (presentation layer)
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
    ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    , [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
    , [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* UI
  * Reactive UI
  * [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit (used for Fragments)
  * [View Binding](https://developer.android.com/topic/libraries/view-binding) - retrieve XML view ids
    (used for [NavHostActivity](app/src/main/java/com/igorwojda/showcase/app/presentation/NavHostActivity.kt) only)
  * [Material Design 3](https://m3.material.io/) - application design system providing UI components
  * Theme selection
    * [Dark Theme](https://material.io/develop/android/theming/dark) - dark theme for the app (Android 10+)
    * [Dynamic Theming](https://m3.material.io/styles/color/dynamic-color/overview) - use generated, wallpaper-based
      theme (Android 12+)
* CI
  * [GitHub Actions](https://github.com/features/actions)
  * Automatic PR verification including tests, linters, and 3rd online tools
* Testing
  * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via
    [android-junit5](https://github.com/mannodermaus/android-junit5)) - test individual classes
  * [Konsist](https://docs.konsist.lemonappdev.com/) - test code conventions and architectural rules
  * [UI Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso)) -
    test user interface (WiP)
  * [Mockk](https://mockk.io/) - mocking framework
  * [Kluent](https://github.com/MarkusAmshove/Kluent) - assertion framework
* Static analysis tools (linters)
  * [Ktlint](https://github.com/pinterest/ktlint) - verify code formatting
  * [Detekt](https://github.com/arturbosch/detekt#with-gradle) - verify code complexity and code smells
  * [Android Lint](http://tools.android.com/tips/lint) - verify Android platform usage
* Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - define build scripts
  * Custom tasks
  * [Gradle Plugins](https://plugins.gradle.org/)
    * [Android Gradle](https://developer.android.com/studio/releases/gradle-plugin) - standard Android Plugins
    * [Test Logger](https://github.com/radarsh/gradle-test-logger-plugin) - format test logs
    * [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - pass data between
      navigation destinations
    * [Android-junit5](https://github.com/mannodermaus/android-junit5) - use [JUnit 5](https://junit.org/junit5/) with Android
  * [Versions catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) - define dependencies
  * [Type safe accessors](https://docs.gradle.org/7.0/release-notes.html)
* GitHub Boots
  * [Renovate](https://github.com/renovatebot/renovate) - automatically update dependencies
  * [Stale](https://github.com/marketplace/stale) - automatically closes stale Issues and Pull Requests that tend to accumulate during a project
* Other Tools
  * Charles Proxy - enabled network traffic sniffing in `debug` builds.

## Architecture

By dividing a problem into smaller and easier-to-solve sub-problems, we can reduce the complexity of designing and
maintaining a large system. Each module is an independent build block serving a clear purpose. We can think about each
feature as a reusable component, the equivalent of [microservice](https://en.wikipedia.org/wiki/Microservices) or private
library.

The modularized code-base approach provides a few benefits:

- reusability - enable code sharing and building multiple apps from the same foundation. Apps should be a sum of their features where the features are organized as separate modules.
- [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns) - each module has a clear API.
  Feature-related classes live in different modules and can't be referenced without explicit module dependency. We
  strictly control what is exposed to other parts of your codebase.
- features can be developed in parallel eg. by different teams
- each feature can be developed in isolation, independently from other features
- faster build time


## Dependency Management

Gradle [versions catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) is used as a
centralized dependency management third-party dependency coordinates (group, artifact, version) are shared across all
modules (Gradle projects and subprojects).

The project enables the `TYPESAFE_PROJECT_ACCESSORS` experimental Gradle feature to generate type-safe accessors to refer other projects.

```kotlin
// Before
implementation(project(":feature_album"))

// After
implementation(projects.featureAlbum)
```

## Design Decisions

Read related articles to have a better understanding of underlying design decisions and various trade-offs.

* [Multiple ways of defining Clean Architecture layers](https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a)
* ...

## What This Project Does Not Cover?

The interface of the app utilizes some of the modern material design components, however, is deliberately kept simple to
focus on application architecture and project config.

## Getting Started

There are a few ways to open this project.

### Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/igorwojda/android-showcase.git` into URL field and press `Clone` button

### Command-line And Android Studio

1. Run `git clone https://github.com/sumitkahar992/PAGING_APP.git` command to clone the project
2. Open `Android Studio` and select `File | Open...` from the menu. Select the cloned directory and press `Open` button

### Plugins

It is recommended to install [Detekt](https://plugins.jetbrains.com/plugin/10761-detekt) to Android Studio. To configure
the plugin open Android Studio preferences, open `Tools`, open `Detekt` and add [detekt.yml](detekt.yml) configuration file.


### Cheatsheet

- [Material Theme Builder](https://m3.material.io/theme-builder#/dynamic) - generate dynamic material theme and see it
  in action
- [Compose Material 3 Components](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary)
  \- a list containing material components
- [Core App Quality Checklist](https://developer.android.com/quality) - learn about building the high-quality app
- [Android Ecosystem Cheat Sheet](https://github.com/igorwojda/android-ecosystem-cheat-sheet) - board containing 200+
  most important tools
- [Kotlin Coroutines - Use Cases on Android](https://github.com/LukasLechnerDev/Kotlin-Coroutine-Use-Cases-on-Android) -
  most popular coroutine usages

### Android Projects

Other high-quality projects will help you to find solutions that work for your project (random order):

- [Compose Samples](https://github.com/android/compose-samples) - repository contains a set of individual Android Studio
- [Jetpack Compose Playground](https://github.com/Foso/Jetpack-Compose-Playground) - This is a Jetpack Compose example
  project
- [Now Android](https://github.com/android/nowinandroid) - fully functional Android app built entirely with Kotlin and
  Jetpack Compose
- [WhatsApp Clone Compose](https://github.com/getStream/whatsApp-clone-compose/) - WhatsApp clone app built with Jetpack
  Compose and Stream Chat SDK for Compose projects to help you learn about Compose in Android
- [Iosched](https://github.com/google/iosched) - official Android application from google IO 2019 and 2021
- [Android Architecture Blueprints v2](https://github.com/googlesamples/android-architecture) - a showcase of various
  Android architecture approaches to developing Android apps
- [Github Browser Sample](https://github.com/googlesamples/android-architecture-components) - multiple small projects
  demonstrating usage of Android Architecture Components
- [Clean Architecture Boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) - clean
  architecture for Android
- [Roxie](https://github.com/ww-tech/roxie) - a solid example of a `common state` approach together with very good
  documentation
- [Kotlin Android Template](https://github.com/cortinico/kotlin-android-template) - the template that lets you create
  preconfigured Android Kotlin project in a few seconds

## Contribute

This project is being maintained to stay up to date with leading industry standards. Please check
the [CONTRIBUTING](CONTRIBUTING.md) page if you want to help.

