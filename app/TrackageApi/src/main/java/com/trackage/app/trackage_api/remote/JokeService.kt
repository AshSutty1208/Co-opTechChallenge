package com.trackage.app.trackage_api.remote

import com.trackage.app.trackage_api.models.MultipleJokes
import com.trackage.app.trackage_api.models.SingleJoke
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