package com.trackage.app.trackage_api.remote

import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.User
import retrofit2.Response
import retrofit2.http.GET

interface TrackageService {
    @GET("user")
    suspend fun getUserDetails() : Response<User>

    @GET("deliveries")
    suspend fun getUserDeliveries() : Response<Deliveries>
}