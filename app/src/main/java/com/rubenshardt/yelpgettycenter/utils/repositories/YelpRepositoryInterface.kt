package com.rubenshardt.yelpgettycenter.utils.repositories

import android.arch.lifecycle.LiveData
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Review
import com.rubenshardt.yelpgettycenter.utils.helpers.SingleLiveEvent

interface YelpRepositoryInterface {

    val businessLiveData: LiveData<Business>
    val reviewsLiveData: LiveData<List<Review>>
    val loadingBusinessLiveData: LiveData<Boolean>
    val loadingReviewsLiveData: LiveData<Boolean>

    fun refreshBusiness(onError: ((Throwable) -> (Unit))?)
    fun refreshReviews(onError: ((Throwable) -> (Unit))?)
}