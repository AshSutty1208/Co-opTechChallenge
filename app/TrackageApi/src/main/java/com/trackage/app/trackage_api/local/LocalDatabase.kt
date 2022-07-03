package com.trackage.app.trackage_api.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.User
import com.trackage.app.trackage_api.utils.Converters

@Database(entities = [User::class, Deliveries::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun trackageDao(): TrackageDao
}