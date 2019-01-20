package com.onemonster.movienotes

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.onemonster.movienotes.di.DaggerAppComponent
import com.onemonster.movienotes.di.NetworkModule
import com.onemonster.movienotes.di.PreferenceModule
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MovieNotesApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) return
            LeakCanary.install(this)

            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
        DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule(BuildConfig.TMDB_API_KEY))
            .preferenceModule(PreferenceModule(packageName))
            .build()
            .inject(this)
    }
}
