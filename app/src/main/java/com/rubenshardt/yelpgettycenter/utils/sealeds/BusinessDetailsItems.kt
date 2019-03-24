package com.rubenshardt.yelpgettycenter.utils.sealeds

import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.model.business.Location
import com.rubenshardt.yelpgettycenter.model.reviews.Review

sealed class BusinessDetailsItems {

    abstract val viewType: Int

    class Header(val business: Business): BusinessDetailsItems() {
        override val viewType = HEADER
    }
    class Map(val location: Location?, val coordinates: Coordinates?): BusinessDetailsItems() {
        override val viewType = MAP
    }
    class ContactInfo(val business: Business): BusinessDetailsItems() {
        override val viewType = CONTACT_INFO
    }
    class Photos(val photosUrls: List<String>): BusinessDetailsItems() {
        override val viewType = PHOTOS
    }
    class ReviewsHeader(): BusinessDetailsItems() {
        override val viewType = REVIEWS_HEADER
    }
    class BusinessReview(val review: Review): BusinessDetailsItems() {
        override val viewType = REVIEW
    }

    companion object {
        const val HEADER = 0
        const val MAP = 1
        const val CONTACT_INFO = 2
        const val PHOTOS = 3
        const val REVIEWS_HEADER = 4
        const val REVIEW = 5
    }
}