package com.trackage.app.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trackage.app.trackage_api.local.LocalDatabase
import com.trackage.app.trackage_api.local.TrackageDao
import com.trackage.app.trackage_api.local.TrackageLocalDataSource
import com.trackage.app.trackage_api.remote.TrackageRemoteDataSource
import com.trackage.app.trackage_api.remote.TrackageService
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.util.DefaultDispatcherProvider
import com.trackage.app.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): LocalDatabase {
        return Room.databaseBuilder(appContext, LocalDatabase::class.java, "trackage")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTrackageService(retrofit: Retrofit): TrackageService = retrofit.create(TrackageService::class.java)

    @Singleton
    @Provides
    fun provideTrackageLocalDataSource(localDatabase: LocalDatabase) = TrackageLocalDataSource(localDatabase)

    @Singleton
    @Provides
    fun provideTrackageRemoteDataSource(trackageService: TrackageService) = TrackageRemoteDataSource(trackageService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TrackageRemoteDataSource,
                          localDataSource: TrackageLocalDataSource) = TrackageRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideDispatcherProvider() : DispatcherProvider = DefaultDispatcherProvider()
}