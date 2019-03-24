package com.rubenshardt.yelpgettycenter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.rubenshardt.yelpgettycenter.model.business.Category

class CategoryConverters {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(value: String?): List<Category>? {
        return value?.let { gson.fromJson(it, Array<Category>::class.java).toList() }
    }

    @TypeConverter
    fun listToString(value: List<Category>?): String? {
        return value?.let { gson.toJson(it) }
    }
}