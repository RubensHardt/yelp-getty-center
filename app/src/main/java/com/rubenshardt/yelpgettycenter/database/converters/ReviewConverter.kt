package com.rubenshardt.yelpgettycenter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.rubenshardt.yelpgettycenter.model.reviews.Review

class ReviewConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(value: String?): List<Review>? {
        return value?.let { gson.fromJson(it, Array<Review>::class.java).toList() }
    }

    @TypeConverter
    fun listToString(value: List<Review>?): String? {
        return value?.let { gson.toJson(it) }
    }
}