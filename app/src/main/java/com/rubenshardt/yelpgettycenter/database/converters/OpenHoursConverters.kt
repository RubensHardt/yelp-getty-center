package com.rubenshardt.yelpgettycenter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.rubenshardt.yelpgettycenter.model.business.OpenHours

class OpenHoursConverters {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(value: String?): List<OpenHours>? {
        return value?.let { gson.fromJson(it, Array<OpenHours>::class.java).toList() }
    }

    @TypeConverter
    fun listToString(value: List<OpenHours>?): String? {
        return value?.let { gson.toJson(it) }
    }
}