package com.rubenshardt.yelpgettycenter.utils.clients

import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.reviews.Reviews
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface YelpClient {

    @GET("businesses/{id}")
    fun fetchBusinessDetails(@Path("id") id: String): Single<Business>

    @GET("businesses/{id}/reviews")
    fun fetchBusinessReviews(@Path("id") id: String): Single<Reviews>
}