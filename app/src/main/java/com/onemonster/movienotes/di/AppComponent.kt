package com.onemonster.movienotes.di

import com.onemonster.movienotes.MovieNotesApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        NetworkModule::class,
        PreferenceModule::class
    ]
)
interface AppComponent : AndroidInjector<MovieNotesApplication> {

    @Component.Builder
    interface Builder {
        fun preferenceModule(preferenceModule: PreferenceModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: MovieNotesApplication)
}