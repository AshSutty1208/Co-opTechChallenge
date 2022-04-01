package com.trackage.app.trackage_api.remote

import com.trackage.app.trackage_api.BaseDataSource
import com.trackage.app.trackage_api.testing.OpenForTesting
import javax.inject.Inject

/**
 * Calls the UserService and returns its Result
 */
@OpenForTesting
class TrackageRemoteDataSource @Inject constructor(
    private val trackageService: TrackageService
): BaseDataSource() {
    suspend fun getUserDetails() = getResult { trackageService.getUserDetails() }
    suspend fun getUserDeliveries() = getResult { trackageService.getUserDeliveries() }
}