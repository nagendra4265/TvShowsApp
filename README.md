# TMDb-Compose-Playground

## Features
* MVVM Architecture + Repository design Pattern.
* Jetpack Libraries and Architecture Components.

## Prerequisites

Add your [TMDB](https://www.themoviedb.org/) API key in the `local.properties` file:
```
tmdb_api_key="22012045e87f46091838788e961af343"
```

## Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Compose](https://developer.android.com/jetpack/compose) Android’s modern toolkit for building native UI.
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ViewModel is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
   * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
   * [Navigation-Compose](https://developer.android.com/jetpack/compose/navigation/) The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component’s infrastructure and features.
   * [Paging-Compose](https://developer.android.com/jetpack/androidx/releases/paging) The Paging Library makes it easier for you to load data gradually and gracefully within your app.
   * [Room](https://developer.android.com/topic/libraries/architecture/room) is a library for data storage persistence which provides an abstraction layer over SQLite.
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Executing code asynchronously.
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) is a state-holder observable flow that emits the current and new state updates to its collectors.
* [Coil-compose](https://coil-kt.github.io/coil/compose/) An image loading library for Android backed by Kotlin Coroutines.
* [Retrofit](https://square.github.io/retrofit/) is a Type-safe HTTP client for Android, Java and Kotlin by Square.
* [Moshi](https://github.com/square/moshi) is a modern JSON library for Android and Java. It makes it easy to parse JSON format data.
* [Gson](https://github.com/google/gson) is a serialization/deserialization library to convert objects into JSON and back.
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses.
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android.

