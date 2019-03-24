package com.rubenshardt.yelpgettycenter.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadCroppedImage(url: String?) {
    Glide.with(this).load(url).apply(RequestOptions().centerCrop()).into(this)
}

fun ImageView.loadRoundedCroppedImage(url: String?, roundingRadius: Int = 16) {
    val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(roundingRadius))
    Glide.with(this).load(url).apply(requestOptions).into(this)
}