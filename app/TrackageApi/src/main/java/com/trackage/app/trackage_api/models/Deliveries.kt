package com.trackage.app.trackage_api.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deliveries")
data class Deliveries(@PrimaryKey val customerId: Int,
                      @SerializedName("deliveries") @Expose val deliveries: List<Delivery>)


data class Delivery(@PrimaryKey val id: Int,
                    @SerializedName("addressOfDelivery") @Expose val addressOfDelivery: Int)