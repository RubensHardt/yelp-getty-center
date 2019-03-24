package com.rubenshardt.yelpgettycenter.utils.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.utils.listeners.BusinessInfoListener
import com.rubenshardt.yelpgettycenter.utils.listeners.MapListener
import com.rubenshardt.yelpgettycenter.utils.listeners.PhotosListener
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems.Companion.CONTACT_INFO
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems.Companion.HEADER
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems.Companion.MAP
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems.Companion.PHOTOS
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems.Companion.REVIEW
import com.rubenshardt.yelpgettycenter.utils.sealeds.BusinessDetailsItems.Companion.REVIEWS_HEADER
import com.rubenshardt.yelpgettycenter.utils.viewholders.*

class BusinessDetailsAdapter(
    private val infoListener: BusinessInfoListener,
    private val photosListener: PhotosListener,
    private val mapListener: MapListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var business: Business? = null
        set(value) {
            field = value
            business?.let {
                items = mutableListOf(
                    BusinessDetailsItems.Header(it),
                    BusinessDetailsItems.Map(it.location, it.coordinates),
                    BusinessDetailsItems.ContactInfo(it),
                    BusinessDetailsItems.Photos(it.photos),
                    BusinessDetailsItems.ReviewsHeader()
                )
                items.addAll(
                    it.topReviews.map { review -> BusinessDetailsItems.BusinessReview(review) }
                )
                notifyDataSetChanged()
            }
        }

    private var items = mutableListOf<BusinessDetailsItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderViewHolder(parent)
            MAP -> MapViewHolder(parent, mapListener)
            CONTACT_INFO -> BusinessInfoViewHolder(parent, infoListener)
            PHOTOS -> PhotosListViewHolder(parent, photosListener)
            REVIEWS_HEADER -> ReviewsHeaderViewHolder(parent)
            REVIEW -> ReviewViewHolder(parent)
            else -> HeaderViewHolder(parent)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.business = (items[position] as? BusinessDetailsItems.Header)?.business
            is MapViewHolder -> with(holder) {
                val mapInfo = (items[position] as? BusinessDetailsItems.Map)
                coordinates = mapInfo?.coordinates
                location = mapInfo?.location
            }
            is BusinessInfoViewHolder -> holder.business =
                (items[position] as? BusinessDetailsItems.ContactInfo)?.business
            is PhotosListViewHolder -> (items[position] as? BusinessDetailsItems.Photos)?.photosUrls?.let {
                holder.photosUrls = it
            }
            is ReviewViewHolder -> holder.review = (items[position] as? BusinessDetailsItems.BusinessReview)?.review
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }
}