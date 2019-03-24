package com.rubenshardt.yelpgettycenter.modules.businessdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.utils.helpers.SingleLiveEvent
import com.rubenshardt.yelpgettycenter.utils.repositories.YelpRepository
import com.rubenshardt.yelpgettycenter.utils.repositories.YelpRepositoryInterface

class BusinessDetailsViewModel(
    private val yelpRepository: YelpRepositoryInterface = YelpRepository
) : ViewModel(), BusinessDetailsInterface {

    override lateinit var businessLiveData: LiveData<Business>
    override val refreshingLiveData = MutableLiveData<Boolean>()
    override val loadingLiveData = MutableLiveData<Boolean>()
    override val errorLiveEvent = SingleLiveEvent<String>()
    override val coordinatesLiveEvent = SingleLiveEvent<Coordinates>()
    override val hoursOfOperationLiveEvent = SingleLiveEvent<Unit>()
    override val phoneNumberLiveEvent = SingleLiveEvent<String>()
    override val externalUrlLiveEvent = SingleLiveEvent<String>()
    override val photoUrlLiveEvent = SingleLiveEvent<String>()

    init {
        loadingLiveData.postValue(true)
        businessLiveData = yelpRepository.getBusiness(
            onSuccess = {
                loadingLiveData.postValue(false)
            },
            onError = ::onError)
    }

    override fun refreshBusinessDetails() {
        refreshingLiveData.postValue(true)
        yelpRepository.refreshBusiness(
            onSuccess = {
                refreshingLiveData.postValue(false)
            },
            onError = ::onError
        )
    }

    override fun onMapClicked(coordinates: Coordinates) {
        coordinatesLiveEvent.postValue(coordinates)
    }

    override fun onHoursOfOperationClicked() {
        hoursOfOperationLiveEvent.call()
    }

    override fun onCallClicked(phoneNumber: String) {
        phoneNumberLiveEvent.postValue(phoneNumber)
    }

    override fun onVisitWebsiteClicked(url: String) {
        externalUrlLiveEvent.postValue(url)
    }

    override fun onPhotoClicked(url: String) {
        photoUrlLiveEvent.postValue(url)
    }

    private fun onError(error: Throwable) {
        errorLiveEvent.postValue(error.localizedMessage)
        refreshingLiveData.postValue(false)
        loadingLiveData.postValue(false)
    }
}