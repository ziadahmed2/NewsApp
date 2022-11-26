# NewsApp

- Please insert API_KEY=YOUR_API_KEY into local.properties file.
- An Android News App using newsapi.org developed with the following choices:
  - Modularization by feature & layer.
  - Kotlin DSL to be able to use kotlin instead of groovy.
  - Inheritance in gradle files and defining dependencies in a central place.
  - MVVM & Clean Architecture.
  - Jetpack Compose as it's the future of Android development.
  - Dagger Hilt for DI.
  - Compose state (state flow may be better since the viewmodel can then support non compose elements like activities or fragments).
  - Channels for one time events instead of sharedflow (Process death).
  - Shared Prefrences used for storing the user's favorite news categories & country.
  - ROOM for saving the news articles the user want to read again.
  - Coroutines for concurrency.
  - Paging 3 for paging the combined results of the top 3 categories sorted by date.
  - Retrofit.
  - Navigation component for compose.
  - Coil For compose.
