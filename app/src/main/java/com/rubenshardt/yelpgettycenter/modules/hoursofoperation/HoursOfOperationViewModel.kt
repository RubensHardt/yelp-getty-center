package com.rubenshardt.yelpgettycenter.modules.hoursofoperation

import android.arch.lifecycle.ViewModel
import com.rubenshardt.yelpgettycenter.utils.repositories.YelpRepository

class HoursOfOperationViewModel : ViewModel(), HoursOfOperationInterface {

    override var businessLiveData = YelpRepository.getBusiness()
}