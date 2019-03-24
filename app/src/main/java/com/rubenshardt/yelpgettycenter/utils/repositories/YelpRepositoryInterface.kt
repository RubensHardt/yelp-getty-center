package com.rubenshardt.yelpgettycenter.utils.repositories

import android.arch.lifecycle.LiveData
import com.rubenshardt.yelpgettycenter.model.business.Business

interface YelpRepositoryInterface {

    fun getBusiness(onSuccess: (() -> Unit)? = null, onError: ((Throwable) -> (Unit))? = null): LiveData<Business>
    fun refreshBusiness(onSuccess: (() -> Unit)? = null, onError: ((Throwable) -> (Unit))? = null)
}