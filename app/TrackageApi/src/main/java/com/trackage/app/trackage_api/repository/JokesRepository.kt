package com.trackage.app.trackage_api.repository

import com.trackage.app.trackage_api.models.SingleJoke
import com.trackage.app.trackage_api.data.remote.JokeRemoteDataSource
import com.trackage.app.trackage_api.utils.Resource
import com.trackage.app.trackage_api.utils.Resource.*
import com.trackage.app.trackage_api.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class JokesRepository @Inject constructor(
    private val remoteDataSource: JokeRemoteDataSource
) {

    suspend fun getRandomJoke() : Resource<SingleJoke> {
        val responseStatus = remoteDataSource.getRandomJoke()
        return handleGet(responseStatus)
    }

    suspend fun getRandomJoke(firstName: String, lastName: String) : Resource<SingleJoke> {
        val responseStatus = remoteDataSource.getRandomJoke(firstName, lastName)
        return handleGet(responseStatus)
    }

    private fun <T> handleGet(resource: Resource<T>) : Resource<T> {
        return if (resource.status == Status.SUCCESS) {
            if (resource.data != null) {
                resource
            } else {
                Resource.error("Response Data was null in JokesRepository")
            }
        } else if (resource.status == Status.ERROR) {
            Resource.error(resource.message ?: "Error Message Null")
        } else {
            Resource.error("Unknown Status in JokesRepository")
        }
    }
}