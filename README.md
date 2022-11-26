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
  - ROOM for saving the news articles the user wants to read again.
  - Coroutines for concurrency.
  - Paging 3 for paging the combined results of the top 3 categories sorted by date.
  - Retrofit.
  - Navigation component for compose [Nested graphs].
  - Coil For compose. 
  
- Application Flow:
  - The user starts in the onboarding section and traverses through it to select his preferences.
  - After the user selects his preferences he is redirected to the Home section of the App. 
    - Further enhancement of the user reselecting his choices can be made.
  - The home section is composed of 3 tabs: 
    - News:
      - For viewing the breaking news from the top 3 categories combined sorted by Date & time and paginated.
      - An article consists of: [Title, Date [Formatted], Image, source newspaper, Short description].
      - On clicking an Article, the user enters a webview screen with a FAB in which he can save the article if he wants to.
      
    - Saved:
      - For viewing the saved articles of the user.
      - If the user clicks on a saved article, it opens the same webview screen but instead of "Saving" it "Removes" the article on clicking the FAB.
      
    - Search:
      - For searching the entire newsapi articles and viewing them sorted by publishing date.
      - Same as the breaking news experience of viewing.
  - On clicking back from any tab other than the News tab, it's always redirected to the News tab.

- Branching Strategy:
  - Branching was made in a manner that supports history lookup & CI/CD.
  - We have the master branch for the latest version of the app.
  - The development branch which we merge our bits and pieces of development code. 
    - Could trigger a pipeline which runs our test cases and lint checks etc...
  - every feature [or part of a feature] or element has its own branch prefixed with version name. ex: [1.0.0/preferences]
    - merged on development to run the neccessary checks. [CI]
  - When the release is ready to enter first testing phase we make a branch prefixed with version name and /staging. ex: [1.0.0/staging]
    - Custom scripting applied to detect version name from this pattern.
    - Deploys an apk to Firebase App distribution for example. [CD]
  - Branch prefixed with version name and /release to trigger the deployment workflow that we setup. ex: [1.0.0/release]
  
