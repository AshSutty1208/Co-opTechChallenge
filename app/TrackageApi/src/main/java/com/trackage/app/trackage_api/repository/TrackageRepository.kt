package com.trackage.app.trackage_api.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.trackage.app.trackage_api.local.TrackageLocalDataSource
import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.User
import com.trackage.app.trackage_api.remote.TrackageRemoteDataSource
import com.trackage.app.trackage_api.testing.OpenForTesting
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import javax.inject.Inject

@OpenForTesting
class TrackageRepository @Inject constructor(
    private val remoteDataSource: TrackageRemoteDataSource,
    private val localDataSource: TrackageLocalDataSource
) {
    fun signupUser(email: String, password: String) {
        try {
            remoteDataSource.signUpUser(email, password)
        } catch (e: Exception) {
            Log.e("Sign Up User Exception", e.message ?: "Null")
        }
    }

    /**
     * Returns true or false based on if the user has successfully logged in
     */
    fun putUserDetails(inputStream: InputStream): Boolean {
        return try {
            val user = getUserDetailsFromFile(inputStream)
            localDataSource.insertUserDetails(user)
            true
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Null")
            false
        }
    }

    suspend fun getUserDetails(): User? {
        return try {
            localDataSource.getUserDetails()
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Null")
            null
        }
    }

    suspend fun getUserDeliveries(): Deliveries? {
        return try {
            localDataSource.getDeliveriesForUser()
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Null")
            null
        }
    }

    fun putUserDeliveries(inputStream: InputStream): Boolean {
        return try {
            val deliveries = getUserDeliveriesFromFile(inputStream)
            localDataSource.insertUserDeliveries(deliveries)
            true
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Null")
            false
        }
    }

    private fun getUserDetailsFromFile(inputStream: InputStream): User {
        // create Gson instance
        val gson = Gson()

        // create a reader
        val reader = JsonReader(InputStreamReader(inputStream))

        // convert JSON file to User
        val user: User = gson.fromJson(reader, User::class.java)

        // close reader
        reader.close()
        inputStream.close()

        return user
    }

    private fun getUserDeliveriesFromFile(inputStream: InputStream): Deliveries {
        // create Gson instance
        val gson = Gson()

        // create a reader
        val reader = JsonReader(InputStreamReader(inputStream))

        // convert JSON file to User
        val deliveries: Deliveries = gson.fromJson(reader, Deliveries::class.java)

        // close reader
        reader.close()
        inputStream.close()

        return deliveries
    }
}