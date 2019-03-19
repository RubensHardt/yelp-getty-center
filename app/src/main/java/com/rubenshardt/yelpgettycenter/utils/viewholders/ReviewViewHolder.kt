package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.model.reviews.Review
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import com.rubenshardt.yelpgettycenter.utils.extensions.loadRoundedCroppedImage
import com.rubenshardt.yelpgettycenter.utils.helpers.DateHelper
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_review)) {

    var review: Review? = null
        set(value) {
            field = value
            with(itemView) {
                profileImageView.loadRoundedCroppedImage(value?.user?.imageUrl)
                nameTextView.text = value?.user?.name
                ratingBar.rating = value?.rating ?: 0F
                timeCreatedTextView.text = value?.timeCreated?.let {
                    val elapsedTime = DateHelper.getElapsedTime(context, it)
                    context.getString(R.string.time_ago, elapsedTime)
                }
                descriptionTextView.text = value?.text
            }
        }
}