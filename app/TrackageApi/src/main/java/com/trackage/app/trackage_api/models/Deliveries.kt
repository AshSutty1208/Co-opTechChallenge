package com.trackage.app.trackage_api.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deliveries")
data class Deliveries(@PrimaryKey val id: Int,
                      @SerializedName("nameOnAccount") @Expose val deliveries: List<Delivery>)


data class Delivery(val id: Int,
                    @SerializedName("nameOnAccount") @Expose val addressOnDelivery: Int)