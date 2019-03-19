package com.rubenshardt.yelpgettycenter.utils.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.utils.listeners.PhotosListener
import com.rubenshardt.yelpgettycenter.utils.viewholders.PhotoViewHolder

class PhotosAdapter(private val listener: PhotosListener) : RecyclerView.Adapter<PhotoViewHolder>() {

    var photosUrls: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(parent, listener)

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.url = photosUrls.getOrNull(position)
    }

    override fun getItemCount() = photosUrls.size
}