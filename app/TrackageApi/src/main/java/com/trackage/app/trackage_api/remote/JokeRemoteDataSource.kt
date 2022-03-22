package com.trackage.app.trackage_api.data.remote

import com.trackage.app.trackage_api.BaseDataSource
import com.trackage.app.trackage_api.testing.OpenForTesting
import com.trackage.app.trackage_api.remote.JokeService
import javax.inject.Inject

/**
 * Calls the JokeService and returns its Result
 */
@OpenForTesting
class JokeRemoteDataSource @Inject constructor(
    private val jokeService: JokeService
): BaseDataSource() {
    suspend fun getRandomJoke() = getResult { jokeService.getRandomJoke() }
    suspend fun getRandomJoke(firstName: String, lastName: String) = getResult { jokeService.getRandomJoke(firstName, lastName) }
    suspend fun getMultipleRandomJokes(amount: Int) = getResult { jokeService.getMultipleRandomJokes(amount) }
}