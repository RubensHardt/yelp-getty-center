package com.rubenshardt.yelpgettycenter.utils.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup?.inflate(@LayoutRes res: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(this?.context).inflate(res, this, attachToRoot)