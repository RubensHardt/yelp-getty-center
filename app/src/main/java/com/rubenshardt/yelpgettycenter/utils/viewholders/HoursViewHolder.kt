package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import com.rubenshardt.yelpgettycenter.utils.helpers.DateHelper
import com.rubenshardt.yelpgettycenter.utils.pokos.DailySchedule
import kotlinx.android.synthetic.main.item_hours.view.*

class HoursViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_hours)) {

    var dailySchedule: DailySchedule? = null
        set(value) {
            field = value
            with(itemView) {
                dayTextView.text = value?.weekDay
                timeTextView.text = value?.hoursOfOperation ?: context.getText(R.string.closed)
            }
        }
}