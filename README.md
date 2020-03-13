# news-fondue-android

news-fondue-android provides top news headlines from the API. The main aim is to show an example of how to build an Android app by Clean Architecture with latest Android Components.



## Architecture

The app uses Clean Architecture to achieve the separation of concerns.

![arch](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/ARCH.jpg?raw=true)

And it also uses a pattern like MVVM in which the *Activities/Fragments* in layer **UI** are **V** and the *ViewModels* in layer **PERSENTER** are **VM**.

The flow of data is as the figure.

![flow](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/flow.jpg?raw=true)



## Libraries and tools

uses libraries and tools used to build Modern Android application, mainly part of Android Jetpack

- Kotlin
- Android Jetpack
  - ViewModel
  - LiveData
  - Paging
  - Room
- Dagger 2
- Retrofit 2
- RxJava 2 / RxAndroid 2
- Timber
- Stetho
- Picasso
- Joda_time
- gson
- okhttp 3

