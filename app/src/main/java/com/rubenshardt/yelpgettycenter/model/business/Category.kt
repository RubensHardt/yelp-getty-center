package com.rubenshardt.yelpgettycenter.model.business

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {

    @Expose
    @SerializedName("alias")
    var alias: String? = null

    @Expose
    @SerializedName("title")
    var title: String? = null
}