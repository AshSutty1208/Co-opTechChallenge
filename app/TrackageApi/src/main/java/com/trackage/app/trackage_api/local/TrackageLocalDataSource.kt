package com.trackage.app.trackage_api.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.trackage.app.trackage_api.BaseDataSource
import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.User
import com.trackage.app.trackage_api.testing.OpenForTesting
import javax.inject.Inject

/**
 * Calls the UserService and returns its Result
 */
@OpenForTesting
class TrackageLocalDataSource @Inject constructor(
    private val localDatabase: LocalDatabase
    ): BaseDataSource() {

    suspend fun getUserDetails(): User {
        return localDatabase.trackageDao().getUser(123)
    }

    fun insertUserDetails(user: User) = localDatabase.trackageDao().insertUser(user)

    suspend fun getDeliveriesForUser(): Deliveries {
        return localDatabase.trackageDao().getUserDeliveries(123)
    }

    fun insertUserDeliveries(deliveries: Deliveries) = localDatabase.trackageDao().insertDeliveries(deliveries)
}