package com.rubenshardt.yelpgettycenter.model.business

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rubenshardt.yelpgettycenter.database.converters.OpenHoursConverters

class BusinessHours {

    @Expose
    @SerializedName("open")
    @TypeConverters(OpenHoursConverters::class)
    var open = listOf<OpenHours>()

    @Expose
    @SerializedName("hours_type")
    @ColumnInfo(name = "hours_type")
    var hoursType: String? = null

    @Expose
    @SerializedName("is_open_now")
    @ColumnInfo(name = "is_open_now")
    var isOpenNow: Boolean? = null
}