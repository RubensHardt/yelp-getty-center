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
    val coordinatesLiveEvent: SingleLiveEvent<Coordinates>
    val hoursOfOperationLiveEvent: SingleLiveEvent<Unit>
    val externalUrlLiveEvent: SingleLiveEvent<String>
    val phoneNumberLiveEvent: SingleLiveEvent<String>
    val photoUrlLiveEvent: SingleLiveEvent<String>

    fun refreshBusinessDetails()
    fun onMapClicked(coordinates: Coordinates)
    fun onHoursOfOperationClicked()
    fun onCallClicked(phoneNumber: String)
    fun onVisitWebsiteClicked(url: String)
    fun onPhotoClicked(url: String)
}