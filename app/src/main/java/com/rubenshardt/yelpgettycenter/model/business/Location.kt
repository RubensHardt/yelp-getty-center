package com.rubenshardt.yelpgettycenter.model.business

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rubenshardt.yelpgettycenter.database.converters.StringListConverter

class Location {

    @Expose
    @SerializedName("address1")
    var address1: String? = null

    @Expose
    @SerializedName("address2")
    var address2: String? = null

    @Expose
    @SerializedName("address3")
    var address3: String? = null

    @Expose
    @SerializedName("city")
    var city: String? = null

    @Expose
    @SerializedName("zip_code")
    @ColumnInfo(name = "zip_code")
    var zipCode: String? = null

    @Expose
    @SerializedName("country")
    var country: String? = null

    @Expose
    @SerializedName("state")
    var state: String? = null

    @Expose
    @SerializedName("display_address")
    @ColumnInfo(name = "display_address")
    @TypeConverters(StringListConverter::class)
    var displayAddress = listOf<String>()

    @Expose
    @SerializedName("cross_streets")
    @ColumnInfo(name = "cross_streets")
    var crossStreets: String? = null
}