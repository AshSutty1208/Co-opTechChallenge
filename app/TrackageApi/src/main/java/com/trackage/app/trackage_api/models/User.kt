package com.trackage.app.trackage_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(@PrimaryKey val id: Int,
                @SerializedName("nameOnAccount") @Expose @ColumnInfo(name = "nameOnAccount") val nameOnAccount: String,
                @SerializedName("email") @Expose @ColumnInfo(name = "email") val email: String,
                @SerializedName("phoneNumber") @Expose @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
//                @SerializedName("addresses") @Expose val addresses: List<Address>,
                @SerializedName("safeLocation") @Expose @ColumnInfo(name = "safeLocation") val safeLocation: String,
                @SerializedName("qr") @Expose @ColumnInfo(name = "qr") val qr: String)