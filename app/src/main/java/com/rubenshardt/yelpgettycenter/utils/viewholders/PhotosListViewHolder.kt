package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.utils.adapters.PhotosAdapter
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import com.rubenshardt.yelpgettycenter.utils.listeners.PhotosListener
import kotlinx.android.synthetic.main.item_photos_list.view.*

class PhotosListViewHolder(parent: ViewGroup, listener: PhotosListener) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.item_photos_list)) {

    private val adapter = PhotosAdapter(listener)

    init {
        itemView.photosRecyclerView.adapter = adapter
    }

    var photosUrls: List<String> = listOf()
        set(value) {
            field = value
            adapter.photosUrls = value
        }
}