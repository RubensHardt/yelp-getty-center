package com.rubenshardt.yelpgettycenter.utils.datasources

import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Reviews
import com.rubenshardt.yelpgettycenter.utils.clients.YelpClient
import com.rubenshardt.yelpgettycenter.utils.constants.ApiConstants
import com.rubenshardt.yelpgettycenter.utils.helpers.RetrofitHelper
import io.reactivex.Single

object YelpDataSource: YelpDataSourceInterface {

    // TODO: Check the possible synchronism issue
    private val client = RetrofitHelper.getRetrofit().create(YelpClient::class.java)

    override fun fetchBusinessDetails(): Single<Business> {
        return client.fetchBusinessDetails(ApiConstants.BUSINESS_ID)
    }

    override fun fetchBusinessReviews(): Single<Reviews> {
        return client.fetchBusinessReviews(ApiConstants.BUSINESS_ID)
    }
}