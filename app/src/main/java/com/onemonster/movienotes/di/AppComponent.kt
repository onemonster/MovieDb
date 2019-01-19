package com.onemonster.movienotes.di

import android.app.Application
import com.onemonster.movienotes.MovieNotesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Suppress("Unused")
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        NetworkModule::class,
        PreferenceModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MovieNotesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun preferenceModule(preferenceModule: PreferenceModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: MovieNotesApplication)
}