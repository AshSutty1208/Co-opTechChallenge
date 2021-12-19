package com.sutton.jokeapp.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sutton.joke_api.data.remote.JokeRemoteDataSource
import com.sutton.joke_api.data.remote.JokeService
import com.sutton.joke_api.data.repository.JokesRepository
import com.sutton.jokeapp.util.DefaultDispatcherProvider
import com.sutton.jokeapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This Object is tasked with creating the whole apps dependencies.
 *
 * This allows hilt to inject the creation of these objects into classes that ask for them.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://api.icndb.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideJokeService(retrofit: Retrofit): JokeService = retrofit.create(JokeService::class.java)

    @Singleton
    @Provides
    fun provideJokeRemoteDataSource(jokeService: JokeService) = JokeRemoteDataSource(jokeService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: JokeRemoteDataSource) = JokesRepository(remoteDataSource)

    @Singleton
    @Provides
    fun provideDispatcherProvider() : DispatcherProvider = DefaultDispatcherProvider()
}