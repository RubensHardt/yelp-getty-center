package com.rubenshardt.yelpgettycenter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

class StringListConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(value: String?): List<String>? {
        return value?.let { gson.fromJson(it, Array<String>::class.java).toList() }
    }

    @TypeConverter
    fun listToString(value: List<String>?): String? {
        return value?.let { gson.toJson(it) }
    }
}