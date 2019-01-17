package com.onemonster.movienotes.di

import android.content.Context
import com.onemonster.movienotes.MovieNotesApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: MovieNotesApplication): Context {
        return application
    }
}