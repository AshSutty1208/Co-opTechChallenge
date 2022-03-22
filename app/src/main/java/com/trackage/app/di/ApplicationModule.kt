package com.trackage.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trackage.app.trackage_api.data.remote.JokeRemoteDataSource
import com.trackage.app.trackage_api.remote.JokeService
import com.trackage.app.trackage_api.repository.JokesRepository
import com.trackage.app.util.DefaultDispatcherProvider
import com.trackage.app.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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