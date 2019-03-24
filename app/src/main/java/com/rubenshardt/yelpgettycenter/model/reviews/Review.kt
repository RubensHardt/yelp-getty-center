package com.rubenshardt.yelpgettycenter.model.reviews

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Review {

    @Expose
    @SerializedName("id")
    var id: String = ""

    @Expose
    @SerializedName("url")
    var url: String? = null

    @Expose
    @SerializedName("text")
    var text: String? = null

    @Expose
    @SerializedName("rating")
    var rating: Float? = null

    @Expose
    @SerializedName("time_created")
    @ColumnInfo(name = "time_created")
    var timeCreated: String? = null

    @Expose
    @SerializedName("user")
    @Embedded
    var user: User? = null
}