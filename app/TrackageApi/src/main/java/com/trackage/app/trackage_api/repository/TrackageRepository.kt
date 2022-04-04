package com.trackage.app.trackage_api.repository

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.trackage.app.trackage_api.local.TrackageLocalDataSource
import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.User
import com.trackage.app.trackage_api.remote.TrackageRemoteDataSource
import com.trackage.app.trackage_api.testing.OpenForTesting
import com.trackage.app.trackage_api.utils.Resource
import com.trackage.app.trackage_api.utils.Resource.*
import java.io.FileReader
import java.lang.Exception
import javax.inject.Inject

@OpenForTesting
class TrackageRepository @Inject constructor(
    private val remoteDataSource: TrackageRemoteDataSource,
    private val localDataSource: TrackageLocalDataSource
) {
    /**
     * Returns true or false based on if the user has successfully logged in
     */
    suspend fun getUserDetails(): Boolean {
        return try {
            val user = getUserDetailsFromFile()
            localDataSource.insertUserDetails(user)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getUserDeliveries() : Resource<Deliveries> {
        val responseStatus = remoteDataSource.getUserDeliveries()
        return handleGet(responseStatus)
    }

    private fun getUserDetailsFromFile(): User {
        // create Gson instance
        val gson = Gson()

        // create a reader
        val reader = JsonReader(FileReader("$USER.json"))

        // convert JSON file to User
        val user: User = gson.fromJson(reader, User::class.java)

        // close reader
        reader.close()

        return user
    }

    private fun <T> handleGet(resource: Resource<T>) : Resource<T> {
        return if (resource.status == Status.SUCCESS) {
            if (resource.data != null) {
                resource
            } else {
                Resource.error("Response Data was null in TrackageRepository")
            }
        } else if (resource.status == Status.ERROR) {
            Resource.error(resource.message ?: "Error Message Null")
        } else {
            Resource.error("Unknown Status in TrackageRepository")
        }
    }

    companion object {
        const val USER = "user"
        const val DELIVERIES = "deliveries"
    }
}