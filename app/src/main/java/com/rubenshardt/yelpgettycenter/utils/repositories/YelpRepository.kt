package com.rubenshardt.yelpgettycenter.utils.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rubenshardt.yelpgettycenter.YelpApplication
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Review
import com.rubenshardt.yelpgettycenter.utils.constants.ApiConstants
import com.rubenshardt.yelpgettycenter.utils.datasources.YelpDataSource
import com.rubenshardt.yelpgettycenter.utils.helpers.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object YelpRepository: YelpRepositoryInterface {

    private var disposables = CompositeDisposable()
    private val database = YelpApplication.database
    private val businessDao = database.businessDao()
    private val reviewsDao = database.reviewsDao()

    override val loadingBusinessLiveData = MutableLiveData<Boolean>()
    override val loadingReviewsLiveData = MutableLiveData<Boolean>()

    override val businessErrorLiveData = SingleLiveEvent<Throwable>()
    override val reviewsErrorLiveData = SingleLiveEvent<Throwable>()

    override fun getBusiness(): LiveData<Business> {
        refreshBusiness()
        return businessDao.getBusiness(ApiConstants.BUSINESS_ID)
    }

    override fun getReviews(): LiveData<List<Review>> {
        refreshReviews()
        return reviewsDao.getReviews()
    }

    private fun refreshBusiness() {
        loadingBusinessLiveData.postValue(true)
        disposables.add(
            YelpDataSource.fetchBusinessDetails()
                .subscribeOn(Schedulers.io())
                .subscribe({ business ->
                    businessDao.insertBusiness(business)
                    loadingBusinessLiveData.postValue(false)
                }, {
                    businessErrorLiveData.postValue(it)
                    loadingBusinessLiveData.postValue(false)
                })
        )
    }

    private fun refreshReviews() {
        loadingReviewsLiveData.postValue(true)
        disposables.add(
            YelpDataSource.fetchBusinessReviews()
                .subscribeOn(Schedulers.io())
                .subscribe ({ reviews ->
                    loadingReviewsLiveData.postValue(false)
                    reviewsDao.insertReviews(reviews.reviews)
                }, {
                    reviewsErrorLiveData.postValue(it)
                    loadingReviewsLiveData.postValue(false)
                })
        )
    }

    override fun refresh() {
        refreshBusiness()
        refreshReviews()
    }
}