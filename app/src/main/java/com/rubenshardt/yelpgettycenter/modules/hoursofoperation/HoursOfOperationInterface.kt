package com.rubenshardt.yelpgettycenter.modules.hoursofoperation

import android.arch.lifecycle.LiveData
import com.rubenshardt.yelpgettycenter.model.business.Business

interface HoursOfOperationInterface {

    val businessLiveData: LiveData<Business>
}