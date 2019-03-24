package com.rubenshardt.yelpgettycenter.utils.customviews

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.rubenshardt.yelpgettycenter.R
import kotlinx.android.synthetic.main.view_business_info.view.*

class BusinessInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_business_info, this, true)
    }

    fun setup(label: String, @DrawableRes iconResource: Int) {
        with(infoTextView) {
            text = label
            setCompoundDrawablesWithIntrinsicBounds(iconResource, 0, 0, 0)
        }
    }
}