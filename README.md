# news-fondue-android

news-fondue-android provides top news headlines from the API. The main aim is to show an example of how to build an Android app by Clean Architecture with latest Android Components.

It also shows how to add Flutter to existing Android app.



![list](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/list.png?raw=true)



![detail](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/detail.png?raw=true)



![flutter_detail](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/flutter_detail.png?raw=true)





## Architecture

The app uses Clean Architecture to achieve the separation of concerns.

![arch](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/ARCH.jpg?raw=true)

And it also uses a pattern like MVVM in which the *Activities/Fragments* in layer **UI** are **V** and the *ViewModels* in layer **PERSENTER** are **VM**.

The flow of data is as the figure.

![flow](https://github.com/chrisfang6/uploads/blob/master/news-fondue-android/flow.jpg?raw=true)



## Libraries and tools

It uses libraries and tools used to build Modern Android application, mainly part of Android Jetpack

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



## Flutter

It uses a flutter module [news_fondue_flutter](https://github.com/chrisfang6/news_fondue_flutter) as a submodule to communicate with native modules and display the news details. See also [7.11 Git Tools - Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules).



## Note

Kotlin plugin version 1.3.70 would cause a problem [Android: LifecycleOwner dep issue? #36160](https://github.com/flutter/flutter/issues/36160). Please use the previous version (1.3.61).