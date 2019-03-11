package com.rubenshardt.yelpgettycenter.utils.datasources

import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Reviews
import io.reactivex.Single

interface YelpDataSourceInterface {

    fun fetchBusinessDetails(): Single<Business>
    fun fetchBusinessReviews(): Single<Reviews>
}