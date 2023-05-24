# MusicPlayerApp

This application made using Deezer Api, you can listen to the songs in the category you want and add the ones you like to your favourites.

# Screens

<table>
  <tr>
    <th>Intro Page</th>
    <th>Sign-In Page</th>
    <th>Sign-Up Page</th>
    <th>Video Calling Page</th>
  </tr>
  <tr>
    <td><img src="pages/intro.jpg" width="200" height="600"/></td>
    <td><img src="pages/sign_in.jpg" width="200" height="600"/></td>
    <td><img src="pages/sign_up.jpg" width="200" height="600"/></td>
    <td><img src="pages/video_calling.jpg" width="200" height="600"/></td>
  </tr>
</table>

<table>
  <tr>
    <th>Home Page</th>
    <th>Message List Page</th>
    <th>Message Page</th>
    <th>Expert Search List Page</th>
  </tr>
  <tr>
    <td><img src="pages/home_page.png" width="200" height="600"/></td>
    <td><img src="pages/message_list.png" width="200" height="600"/></td>
    <td><img src="pages/message.png" width="200" height="600"/></td>
    <td><img src="pages/expert_search_list.png" width="200" height="600"/></td>
  </tr>
</table>

<table>
  <tr>
    <th>Expert Detail Page</th>
    <th>Expert Appointment Page</th>
    <th>Expert Appointment Time Page</th>
    <th>Expert Appointment Time and Date Page</th>
  </tr>
  <tr>
    <td><img src="pages/expert_search_detail.png" width="200" height="600"/></td>
    <td><img src="pages/expert_appointment.png" width="200" height="600"/></td>
    <td><img src="pages/expert_appointment_time.png" width="200" height="600"/></td>
    <td><img src="pages/expert_appointment_time_and_date.png" width="200" height="600"/></td>
  </tr>
</table>

<table>
  <tr>
    <th>Expert Profile Page</th>
    <th>User Profil Page</th>
  </tr>
  <tr>
    <td><img src="pages/expert_profile.png" width="200" height="600"/></td>
    <td><img src="pages/user_profile.png" width="200" height="600"/></td>
  </tr>
</table>



# Project Tech stack & Open-source libraries

1. [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html): Clean Architecture is an architectural pattern for designing software systems.
   - Data Layer: While the UI layer contains UI-related state and UI logic, the data layer contains application data and business logic. The business logic is what givesvalue to your app—it's made of real-world business rules that determine how application data must be created, stored, and changed.
   - Domain Layer: The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels. This layer  is optional because not all apps will have these requirements. You should only use it when needed-for example, to handle complexity or favor reusability.
   - UI Layer: The role of the UI is to display the application data on the screen and also to serve as the primary point of user interaction. Whenever the data c changes,    either due to user interaction (like pressing a button) or external input (like a network response), the UI should update to reflect those changes. Effectively, the    UI is a visual representation of the application state as retrieved from the data layer.
- [Flow](https://developer.android.com/kotlin/flow) : Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Room](https://developer.android.com/training/data-storage/room) : Room is a persistence library provided by Google as part of the Android Jetpack components. Room simplifies the process of working with a SQLite database in your Android app by providing an abstraction layer over the traditional SQLite API.
- [Lottie](https://lottiefiles.com/animation/loading?page=3):  Lottie provides a simple and efficient way to play animations created in After Effects directly in your Android app. It supports various animation features such as keyframes, easing, opacity, masks, and more. 
- [Custom View](https://developer.android.com/develop/ui/views/layout/custom-views/custom-components): Android offers a sophisticated and powerful componentized model for building your UI, based on the fundamental layout classes: View and ViewGroup. To start with, the platform includes a variety of prebuilt View and ViewGroup subclasses — called widgets and layouts, respectively — that you can use to construct your UI.
- [Material Design](https://m3.material.io/): Material Design is a design system built and supported by Google designers and developers. Material.io includes in-depth UX guidance and UI component implementations for Android, Flutter, and the Web.
- [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel): The ViewModel class is a business logic or screen level state holder. It exposes state to the UI and encapsulates related business logic. Its principal advantage is that it caches state and persists it through configuration changes. This means that your UI doesn’t have to fetch data again when navigating between activities, or following configuration changes, such as when rotating the screen.
- [Glide](https://github.com/bumptech/glide): Glide is a popular open-source image loading and caching library for Android applications. It provides a simple and efficient way to load, display, and cache images from various sources, such as network URLs, local files, and resources.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) : Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.
- [Retrofit](https://square.github.io/retrofit/): Retrofit is a popular open-source library for Android and Java that makes it easy to consume RESTful web services. It was developed by Square, the same company that created OkHttp, another popular networking library for Android.
- [Navigation](https://developer.android.com/guide/navigation): Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app. Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
- [Fragments](https://developer.android.com/guide/fragments) : A Fragment represents a reusable portion of your app's UI. A fragment defines and manages its own layout, has its own lifecycle, and can handle its own input events. Fragments cannot live on their own--they must be hosted by an activity or another fragment. The fragment’s view hierarchy becomes part of, or attaches to, the host’s view hierarchy.
- [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines) : A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously. Coroutines were added to Kotlin in version 1.3 and are based on established concepts from other languages.
  On Android, coroutines help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive. Over 50% of professional developers who use coroutines have reported seeing increased productivity. This topic describes how you can use Kotlin coroutines to address these problems, enabling you to write cleaner and more concise app code.
- [ViewBinding](https://developer.android.com/topic/libraries/data-binding): View binding is a feature that allows you to more easily write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
- [ExoPlayer](https://developer.android.com/guide/topics/media/exoplayer): ExoPlayer is an open-source media player library developed by Google. It is specifically designed for playing audio and video content in Android applications. ExoPlayer provides a flexible and extensible framework that allows developers to easily integrate media playback functionality into their apps.





