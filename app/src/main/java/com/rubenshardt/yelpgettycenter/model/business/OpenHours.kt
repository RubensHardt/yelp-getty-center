package com.rubenshardt.yelpgettycenter.model.business

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OpenHours {

    @Expose
    @SerializedName("is_overnight")
    @ColumnInfo(name = "is_overnight")
    var isOvernight: Boolean? = null

    @Expose
    @SerializedName("start")
    var start: String? = null

    @Expose
    @SerializedName("end")
    var end: String? = null

    @Expose
    @SerializedName("day")
    var day: Int? = null
}