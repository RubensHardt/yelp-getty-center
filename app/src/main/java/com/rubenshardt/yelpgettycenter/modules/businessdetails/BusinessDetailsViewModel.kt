package com.rubenshardt.yelpgettycenter.modules.businessdetails

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.utils.helpers.SingleLiveEvent
import com.rubenshardt.yelpgettycenter.utils.repositories.YelpRepository
import com.rubenshardt.yelpgettycenter.utils.repositories.YelpRepositoryInterface

class BusinessDetailsViewModel(
    private val yelpRepository: YelpRepositoryInterface = YelpRepository
) : ViewModel(), BusinessDetailsInterface {

    override var businessLiveData = yelpRepository.businessLiveData
    override var reviewsLiveData = yelpRepository.reviewsLiveData
    override val refreshingLiveData = MediatorLiveData<Boolean>()
    override val loadingLiveData = MutableLiveData<Boolean>()
    override val errorLiveEvent = SingleLiveEvent<String>()
    override val coordinatesLiveEvent = SingleLiveEvent<Coordinates>()
    override val hoursOfOperationLiveEvent = SingleLiveEvent<Unit>()
    override val phoneNumberLiveEvent = SingleLiveEvent<String>()
    override val externalUrlLiveEvent = SingleLiveEvent<String>()
    override val photoUrlLiveEvent = SingleLiveEvent<String>()

    init {
        //Shows the loading if it's loading the business for the first time, if not, shows the refreshing loading
        refreshingLiveData.addSource(yelpRepository.loadingBusinessLiveData) { isBusinessLoading ->
            val isLoading = isBusinessLoading ?: false
            val hasLoadedValues = businessLiveData.value != null
            loadingLiveData.postValue(isLoading && !hasLoadedValues)
            refreshingLiveData.postValue(isLoading && hasLoadedValues)
        }
        //Shows the refreshing loading if the business was already fetched, otherwise shows nothing
        refreshingLiveData.addSource(yelpRepository.loadingReviewsLiveData) { areReviewsLoading ->
            val isLoading = areReviewsLoading ?: false
            val hasLoadedValues = businessLiveData.value != null
            if (isLoading && hasLoadedValues) {
                refreshingLiveData.postValue(true)
            }
        }
    }

    override fun refreshBusinessDetails() {
        yelpRepository.refreshBusiness(onError = ::onError)
        yelpRepository.refreshReviews(onError = ::onError)
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
        refreshingLiveData.postValue(false)
        loadingLiveData.postValue(false)
        errorLiveEvent.postValue(error.localizedMessage)
    }
}