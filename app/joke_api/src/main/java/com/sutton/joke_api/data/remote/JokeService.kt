package com.sutton.joke_api.data.remote

import com.sutton.joke_api.data.models.MultipleJokes
import com.sutton.joke_api.data.models.SingleJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeService {
    @GET("jokes/random")
    suspend fun getRandomJoke() : Response<SingleJoke>

    @GET("jokes/random")
    suspend fun getRandomJoke(@Query("firstName") firstName: String,
                              @Query("lastName") lastName: String) : Response<SingleJoke>

    @GET("jokes/random/{number}")
    suspend fun getMultipleRandomJokes(@Path("number") number: Int): Response<MultipleJokes>
}