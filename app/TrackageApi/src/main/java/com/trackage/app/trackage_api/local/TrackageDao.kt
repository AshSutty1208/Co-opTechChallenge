package com.trackage.app.trackage_api.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.User

@Dao
interface TrackageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeliveries(deliveries: Deliveries)

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUser(id: Int)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUser(id: Int): User

    @Query("SELECT * FROM deliveries WHERE customerId = :id")
    suspend fun getUserDeliveries(id: Int): Deliveries
}