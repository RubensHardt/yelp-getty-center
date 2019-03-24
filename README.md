# yelp-getty-center
This app should serve as a single-screen Yelp! Listing for The Getty Center, in Los Angeles, CA. 

It uses the Yelp! Fusion API (https://www.yelp.com/fusion) to display the following basic information:
 
- Star rating
- Total Reviews
- Address
- Phone
- Website
- Hours of Operation
- Top 3 reviews
- Top 3 photos

The app was build using MVVM pattern with repositories and jetpack components, like described in android developer guide to app architecture:
https://developer.android.com/jetpack/docs/guide.

The app was developed using:
  - Kotlin 1.3.21
  - Android Studio 3.3.2
  - Room for the local persistence
  - RxJava with Retrofit for the data requests
  - ViewModels and LiveDatas
  - Glide for image loadings
  - Google maps for maps
  - Joda-time-android as a replacement for the Java date and time classes
  - Mockito-kotlin for unit tests
  
In order to run this app, clone this repository and run it on Android Studio.
