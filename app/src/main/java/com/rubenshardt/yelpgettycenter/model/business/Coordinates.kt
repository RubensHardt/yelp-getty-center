package com.rubenshardt.yelpgettycenter.model.business

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinates {

    @Expose
    @SerializedName("latitude")
    var latitude: Double? = null

    @Expose
    @SerializedName("longitude")
    var longitude: Double? = null
}