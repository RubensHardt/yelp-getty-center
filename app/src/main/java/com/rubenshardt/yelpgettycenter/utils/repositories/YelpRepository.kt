package com.rubenshardt.yelpgettycenter.utils.repositories

import android.arch.lifecycle.MutableLiveData
import com.rubenshardt.yelpgettycenter.YelpApplication
import com.rubenshardt.yelpgettycenter.utils.constants.ApiConstants
import com.rubenshardt.yelpgettycenter.utils.datasources.YelpDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object YelpRepository: YelpRepositoryInterface {

    private var disposables = CompositeDisposable()
    private val database = YelpApplication.database
    private val businessDao = database.businessDao()
    private val reviewsDao = database.reviewsDao()

    override val businessLiveData = businessDao.getBusiness(ApiConstants.BUSINESS_ID)
    override val reviewsLiveData = reviewsDao.getReviews(ApiConstants.BUSINESS_ID)

    override val loadingBusinessLiveData = MutableLiveData<Boolean>()
    override val loadingReviewsLiveData = MutableLiveData<Boolean>()

    override fun refreshBusiness(onError: ((Throwable) -> (Unit))?) {
        loadingBusinessLiveData.postValue(true)
        disposables.add(
            YelpDataSource.fetchBusinessDetails()
                .subscribeOn(Schedulers.io())
                .subscribe({ business ->
                    businessDao.insertBusiness(business)
                    loadingBusinessLiveData.postValue(false)
                }, {
                    onError?.invoke(it)
                    loadingBusinessLiveData.postValue(false)
                })
        )
    }

    override fun refreshReviews(onError: ((Throwable) -> (Unit))?) {
        loadingReviewsLiveData.postValue(true)
        disposables.add(
            YelpDataSource.fetchBusinessReviews()
                .subscribeOn(Schedulers.io())
                .subscribe ({ reviews ->
                    loadingReviewsLiveData.postValue(false)
                    reviews.reviews.forEach {
                        it.businessId = ApiConstants.BUSINESS_ID
                    }
                    reviewsDao.insertReviews(reviews.reviews)
                }, {
                    onError?.invoke(it)
                    loadingReviewsLiveData.postValue(false)
                })
        )
    }
}