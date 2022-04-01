package com.trackage.app.trackage_api.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(@PrimaryKey val id: Int,
                @SerializedName("nameOnAccount") @Expose val nameOnAccount: String,
                @SerializedName("email") @Expose val email: String,
                @SerializedName("phoneNumber") @Expose val phoneNumber: String,
                @SerializedName("addresses") @Expose val addresses: List<Address>,
                @SerializedName("safeLocation") @Expose val safeLocation: String,
                @SerializedName("qr") @Expose val qr: String)