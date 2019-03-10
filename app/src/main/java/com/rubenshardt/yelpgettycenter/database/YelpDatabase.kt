package com.rubenshardt.yelpgettycenter.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.rubenshardt.yelpgettycenter.database.converters.BusinessHoursConverter
import com.rubenshardt.yelpgettycenter.database.converters.CategoryConverters
import com.rubenshardt.yelpgettycenter.database.converters.OpenHoursConverters
import com.rubenshardt.yelpgettycenter.database.converters.StringListConverter
import com.rubenshardt.yelpgettycenter.database.daos.BusinessDao
import com.rubenshardt.yelpgettycenter.database.daos.ReviewsDao
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Review

@Database(entities = [Business::class, Review::class], version = 1)
@TypeConverters(CategoryConverters::class, OpenHoursConverters::class, StringListConverter::class, BusinessHoursConverter::class)
abstract class YelpDatabase: RoomDatabase() {

    abstract fun businessDao(): BusinessDao
    abstract fun reviewsDao(): ReviewsDao
}