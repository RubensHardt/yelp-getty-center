package com.rubenshardt.yelpgettycenter.modules.businessdetails

import android.arch.lifecycle.LiveData
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.model.reviews.Review
import com.rubenshardt.yelpgettycenter.utils.helpers.SingleLiveEvent

interface BusinessDetailsInterface {

    val businessLiveData: LiveData<Business>
    val reviewsLiveData: LiveData<List<Review>>
    val refreshingLiveData: LiveData<Boolean>
    val loadingLiveData: LiveData<Boolean>
    val errorLiveEvent: SingleLiveEvent<String>

    fun refreshBusinessDetails()
}