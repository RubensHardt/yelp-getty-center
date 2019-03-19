package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_header.view.*

class HeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_header)) {

    var business: Business? = null
        set(value) {
            field = value
            with(itemView) {
                ratingBar.rating = value?.rating ?: 0F
                tagsTextView.text = value?.categories?.map { it.title }?.joinToString(" ")
                reviewsTextView.text = context.getString(R.string.reviews_count, business?.reviewCount)
            }
        }
}