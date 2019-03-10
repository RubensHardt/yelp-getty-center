package com.rubenshardt.yelpgettycenter.model.reviews

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Reviews {

    @Expose
    @SerializedName("reviews")
    var reviews = listOf<Review>()

    @Expose
    @SerializedName("total")
    var total: Int? = null
}