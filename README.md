# MovieDb
Practice rx, room, livedata, recyclerview pagination, retrofit, moshi, dagger2, repository pattern

## Building on your environment
Add your tmdb API key in your local.properties file.
```
tmdb_api_key=YOUR_API_KEY
```

## Stack
- [Dagger2](https://github.com/google/dagger) for dependency injection
- [Retrofit2](https://github.com/square/retrofit) for constructing the REST API
- [Moshi](https://github.com/square/moshi) for handling json
- [RxJava2](https://github.com/ReactiveX/RxJava) for reactiveX used in networking and repository
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for lifecycle safe observables
- [Glide](https://github.com/bumptech/glide) for loading images
- [Timber](https://github.com/JakeWharton/timber) for logging
- [Stetho](https://github.com/facebook/stetho) for debugging persistence data & network packets
- [Room](https://developer.android.com/topic/libraries/architecture/room) for sqlite persistence

## Architecture
- MVVM
- Repository pattern
- Repository sends data to viewmodel using rx, viewmodel sends data to activity using livedata
