package com.trackage.app.trackage_api.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.trackage.app.trackage_api.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            if (instance == null) {
                instance = buildDatabase(context)
            }

            // We assert that this cannot ever be null
            return instance!!
        }

        private fun buildDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(context, LocalDatabase::class.java, "applicants")
                .build()
        }
    }
}