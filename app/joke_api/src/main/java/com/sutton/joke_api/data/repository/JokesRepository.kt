package com.sutton.joke_api.data.repository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sutton.joke_api.data.models.MultipleJokes
import com.sutton.joke_api.data.models.SingleJoke
import com.sutton.joke_api.data.remote.JokeRemoteDataSource
import com.sutton.joke_api.utils.Resource
import com.sutton.joke_api.utils.Resource.*
import com.sutton.jokeapp.testing.OpenForTesting
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException
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