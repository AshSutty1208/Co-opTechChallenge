package com.trackage.app.trackage_api.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.trackage.app.trackage_api.BaseDataSource
import com.trackage.app.trackage_api.models.User
import com.trackage.app.trackage_api.testing.OpenForTesting
import javax.inject.Inject

/**
 * Calls the UserService and returns its Result
 */
@OpenForTesting
class TrackageLocalDataSource @Inject constructor(
    private val trackageDao: TrackageDao
    ): BaseDataSource() {

    suspend fun getUserDetails(): User {
        return trackageDao.getUser(1)
    }

    suspend fun insertUserDetails(user: User) = trackageDao.insertUser(user)
}