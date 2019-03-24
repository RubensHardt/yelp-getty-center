package com.rubenshardt.yelpgettycenter.model.business

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Messaging {

    @Expose
    @SerializedName("url")
    var url: String? = null

    @Expose
    @SerializedName("use_case_text")
    @ColumnInfo(name = "use_case_text")
    var useCaseText: String? = null
}