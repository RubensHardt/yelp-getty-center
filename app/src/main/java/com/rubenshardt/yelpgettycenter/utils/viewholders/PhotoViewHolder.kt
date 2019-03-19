package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import com.rubenshardt.yelpgettycenter.utils.extensions.loadRoundedCroppedImage
import com.rubenshardt.yelpgettycenter.utils.listeners.PhotosListener
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder(parent: ViewGroup, private val listener: PhotosListener) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.item_photo)) {

    init {
        itemView.setOnClickListener {
            url?.let { listener.onPhotoClicked(it) }
        }
    }

    var url: String? = null
        set(value) {
            field = value
            itemView.photoImageView.loadRoundedCroppedImage(value)
        }
}