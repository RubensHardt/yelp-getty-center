package com.rubenshardt.yelpgettycenter.model.business

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rubenshardt.yelpgettycenter.database.converters.BusinessHoursConverter
import com.rubenshardt.yelpgettycenter.database.converters.CategoryConverters
import com.rubenshardt.yelpgettycenter.database.converters.ReviewConverter
import com.rubenshardt.yelpgettycenter.database.converters.StringListConverter
import com.rubenshardt.yelpgettycenter.model.reviews.Review

@Entity(tableName = "business")
class Business {

    @PrimaryKey
    @Expose
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String = ""

    @Expose
    @SerializedName("alias")
    var alias: String? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String? = null

    @Expose
    @SerializedName("is_closed")
    @ColumnInfo(name = "is_closed")
    var isClosed: Boolean? = null

    @Expose
    @SerializedName("url")
    var url: String? = null

    @Expose
    @SerializedName("phone")
    var phone: String? = null

    @Expose
    @SerializedName("display_phone")
    @ColumnInfo(name = "display_phone")
    var displayPhone: String? = null

    @Expose
    @SerializedName("review_count")
    @ColumnInfo(name = "review_count")
    var reviewCount: Int? = null

    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryConverters::class)
    var categories = listOf<Category>()

    @Expose
    @SerializedName("rating")
    var rating: Float? = null

    @Expose
    @SerializedName("location")
    @Embedded
    var location: Location? = null

    @Expose
    @SerializedName("coordinates")
    @Embedded
    var coordinates: Coordinates? = null

    @Expose
    @SerializedName("photos")
    @TypeConverters(StringListConverter::class)
    var photos = listOf<String>()

    @Expose
    @SerializedName("hours")
    @TypeConverters(BusinessHoursConverter::class)
    var hours = listOf<BusinessHours>()

    @Expose
    @SerializedName("messaging")
    @Embedded(prefix = "messaging_")
    var messaging: Messaging? = null

    @TypeConverters(ReviewConverter::class)
    var topReviews = listOf<Review>()
}