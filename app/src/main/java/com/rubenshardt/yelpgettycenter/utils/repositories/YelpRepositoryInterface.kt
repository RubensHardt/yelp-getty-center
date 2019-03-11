package com.rubenshardt.yelpgettycenter.utils.repositories

import android.arch.lifecycle.LiveData
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Review
import com.rubenshardt.yelpgettycenter.utils.helpers.SingleLiveEvent

interface YelpRepositoryInterface {

    val loadingBusinessLiveData: LiveData<Boolean>
    val loadingReviewsLiveData: LiveData<Boolean>
    val businessErrorLiveData: SingleLiveEvent<Throwable>
    val reviewsErrorLiveData: SingleLiveEvent<Throwable>

    fun getBusiness(): LiveData<Business>
    fun getReviews(): LiveData<List<Review>>
    fun refresh()
}