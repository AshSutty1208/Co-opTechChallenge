package com.trackage.app.trackage_api.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trackage.app.trackage_api.models.Address
import com.trackage.app.trackage_api.models.Deliveries
import com.trackage.app.trackage_api.models.Delivery
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun addressListToJson(value: List<Address>?): String = Gson().toJson(value)

    @TypeConverter
    fun addressJsonToList(value: String) = Gson().fromJson(value, Array<Address>::class.java).toList()

    @TypeConverter
    fun deliveriesListToJson(value: List<Delivery>?): String = Gson().toJson(value)

    @TypeConverter
    fun deliveriesJsonToList(value: String) = Gson().fromJson(value, Array<Delivery>::class.java).toList()
}