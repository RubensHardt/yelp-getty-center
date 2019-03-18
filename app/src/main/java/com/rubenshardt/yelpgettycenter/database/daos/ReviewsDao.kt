package com.rubenshardt.yelpgettycenter.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rubenshardt.yelpgettycenter.model.reviews.Review

@Dao
interface ReviewsDao {

    @Query("SELECT * FROM reviews where business_id =:businessId")
    fun getReviews(businessId: String): LiveData<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReviews(reviews: List<Review>)
}