[![kotlin](https://img.shields.io/badge/Kotlin-100%25-blueviolet)](https://kotlinlang.org/) [![Android API](https://img.shields.io/badge/api-21%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=21) [![Build status](https://build.appcenter.ms/v0.1/apps/86493375-a9cd-491f-a2b3-43176506e6d2/branches/main/badge)](https://appcenter.ms)

# Note App

## :star: Features

- [x] Display Notes

:runner: For run the app just clone the repository and execute the app on Android Studio.

### Requirements to install the app

- Using phones with Android Api 21+

##### This application was developed using Kotlin and uses the following components:

- Jetpack compose
- Coroutines
- Clean architecture (Domain, Data, UI)
- MVVM
- Repository pattern
- StateFlow
- Dagger Hilt (Dependency injection)
- Unit testing
- Moshi
- Mvvm

## :dart: Architecture

The application is built using Clean Architecture pattern based
on [Architecture Components](https://developer.android.com/jetpack/guide#recommended-app-arch) on
Android. The application is divided into three layers:

![Clean Architecture](https://devexperto.com/wp-content/uploads/2018/10/clean-architecture-own-layers.png)

- Domain: This layer contains the business logic of the application, here we define the data models
  and the use cases.
- Data: This layer contains the data layer of the application. It contains the database, network and
  the repository implementation.
- UI: This layer contains the presentation layer of the application with elements composable,
  viewmodel etc.

## License

MIT

**Geral**
