package com.rubenshardt.yelpgettycenter.utils.listeners

interface BusinessInfoListener {

    fun onHoursOfOperationClicked()
    fun onCallClicked(phoneNumber: String)
    fun onVisitWebsiteClicked(url: String)
}