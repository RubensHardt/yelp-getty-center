package com.rubenshardt.yelpgettycenter.utils.listeners

import com.rubenshardt.yelpgettycenter.model.business.Coordinates

interface MapListener {
    fun onMapClicked(coordinates: Coordinates)
}