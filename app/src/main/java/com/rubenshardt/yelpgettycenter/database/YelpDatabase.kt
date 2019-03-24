package com.rubenshardt.yelpgettycenter.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.rubenshardt.yelpgettycenter.database.converters.*
import com.rubenshardt.yelpgettycenter.database.daos.BusinessDao
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Review

@Database(entities = [Business::class], version = 1, exportSchema = false)
@TypeConverters(CategoryConverters::class, OpenHoursConverters::class, StringListConverter::class, BusinessHoursConverter::class, ReviewConverter::class)
abstract class YelpDatabase: RoomDatabase() {

    abstract fun businessDao(): BusinessDao
}