package com.rubenshardt.yelpgettycenter.model.reviews

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

//    @Expose
//    @SerializedName("id")
//    var id: String? = null

    @Expose
    @SerializedName("profile_url")
    @ColumnInfo(name = "profile_url")
    var profileUrl: String? = null

    @Expose
    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String? = null

    @Expose
    @SerializedName("name")
    var name: String? = null
}