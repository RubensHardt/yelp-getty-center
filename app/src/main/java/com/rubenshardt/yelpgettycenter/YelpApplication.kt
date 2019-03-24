package com.rubenshardt.yelpgettycenter

import android.app.Application
import android.arch.persistence.room.Room
import com.rubenshardt.yelpgettycenter.database.YelpDatabase

class YelpApplication: Application() {

    companion object {
        lateinit var database: YelpDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, YelpDatabase::class.java,"yelp-db").build()
    }
}