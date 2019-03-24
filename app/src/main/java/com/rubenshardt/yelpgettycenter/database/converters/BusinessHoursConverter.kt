package com.rubenshardt.yelpgettycenter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.rubenshardt.yelpgettycenter.model.business.BusinessHours

class BusinessHoursConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(value: String?): List<BusinessHours>? {
        return value?.let { gson.fromJson(it, Array<BusinessHours>::class.java).toList() }
    }

    @TypeConverter
    fun listToString(value: List<BusinessHours>?): String? {
        return value?.let { gson.toJson(it) }
    }
}