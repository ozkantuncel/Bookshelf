# Bookshelf 

I made Book shop App with using `Jetpack Compose` and [Firebase][1] (Firestore, firebase authentication, Realtime Database, Storage).

## Libraries Used
---
* [Architecture][2]: In Compose the UI is immutable—there's no way to update it after it's been drawn.
* [Lifecycle][3]: A Composition is a tree-structure of the composables that describe your UI.
* [Navigation][4]: You can navigate between composables.
* [ViewModel][5]: This is important to have your previously loaded data instantly available when you return to your destination
* [Repository][6]: Repository modules handle data operations.
* [Jetpack-Compose][7]: Jetpack Compose is a modern toolkit for building native Android UI.
* [Gson][8]: For convert Java Objects into their JSON representation and between composables data transfer.
* [Flow][9]: Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
* [Kotlin-Parcelize][11]: The kotlin-parcelize plugin provides a Parcelable implementation generator.
* [Hawk][12]: Secure, simple key-value storage for android
* [Dagger-Hilt][13]: Hilt is the recommended solution for dependency injection in Android apps, and works seamlessly with Compose.
* [Splash-API][14]: Provides control over the splash screen once the application is started.On API 31+ (Android 12+) this class calls the platform methods.
* [Datastore][15]: Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
* [Pager-Indicators][16]: Layout manager that allows the user to flip left and right through pages of data. You supply an implementation of a PagerAdapter to generate the pages that the view shows.
* [Coil][17]: For image loading
---
App video

---

```
MIT License

Copyright (c) 2023 Özkan TUNCEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
[1]:https://firebase.google.com/
[2]:https://developer.android.com/jetpack/compose/architecture
[3]:https://developer.android.com/jetpack/compose/lifecycle
[4]:https://developer.android.com/jetpack/compose/navigation
[5]:https://developer.android.com/jetpack/compose/state#viewmodel-state
[6]:https://developer.android.com/jetpack/guide#fetch-data
[7]:https://developer.android.com/jetpack/compose/tutorial
[8]:https://github.com/google/gson
[9]:https://developer.android.com/kotlin/flow
[11]:https://developer.android.com/kotlin/parcelize
[12]:https://github.com/orhanobut/hawk
[13]:https://developer.android.com/jetpack/compose/libraries#hilt
[14]:https://developer.android.com/develop/ui/views/launch/splash-screen/migrate
[15]:https://developer.android.com/topic/libraries/architecture/datastore
[16]:https://google.github.io/accompanist/pager/
[17]:https://coil-kt.github.io/coil/compose/