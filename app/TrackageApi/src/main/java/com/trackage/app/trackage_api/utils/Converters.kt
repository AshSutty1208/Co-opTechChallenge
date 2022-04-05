package com.trackage.app.trackage_api.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trackage.app.trackage_api.models.Address
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun listToJson(value: List<Address>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Address>::class.java).toList()
}