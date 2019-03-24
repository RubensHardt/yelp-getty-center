package com.rubenshardt.yelpgettycenter.utils.repositories

import android.arch.lifecycle.LiveData
import com.rubenshardt.yelpgettycenter.YelpApplication
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Reviews
import com.rubenshardt.yelpgettycenter.utils.constants.ApiConstants
import com.rubenshardt.yelpgettycenter.utils.datasources.YelpDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

object YelpRepository : YelpRepositoryInterface {

    private var disposables = CompositeDisposable()
    private val database = YelpApplication.database
    private val businessDao = database.businessDao()

    override fun getBusiness(onSuccess: (() -> Unit)?, onError: ((Throwable) -> (Unit))?): LiveData<Business> {
        refreshBusiness(onSuccess, onError)
        return businessDao.getBusiness(ApiConstants.BUSINESS_ID)
    }

    override fun refreshBusiness(onSuccess: (() -> Unit)?, onError: ((Throwable) -> (Unit))?) {
        val businessObservable = YelpDataSource.fetchBusinessDetails()
        val reviewsObservable = YelpDataSource.fetchBusinessReviews()
        disposables.add(
            Single.zip(
                businessObservable,
                reviewsObservable,
                BiFunction<Business, Reviews, Business> { business, reviews ->
                    business.topReviews = reviews.reviews
                    business
                }
            )
                .subscribeOn(Schedulers.io())
                .subscribe({ business ->
                    businessDao.insertBusiness(business)
                    onSuccess?.invoke()
                }, {
                    onError?.invoke(it)
                })
        )
    }
}