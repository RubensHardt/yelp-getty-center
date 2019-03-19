package com.rubenshardt.yelpgettycenter.utils.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.model.business.BusinessHours
import com.rubenshardt.yelpgettycenter.model.business.OpenHours
import com.rubenshardt.yelpgettycenter.utils.pokos.DailySchedule
import com.rubenshardt.yelpgettycenter.utils.viewholders.HoursViewHolder

class HoursAdapter(daysOfWeek: Array<String>): RecyclerView.Adapter<HoursViewHolder>() {

    var openHoursList = listOf<OpenHours>()
        set(value) {
            field = value
            dailySchedule.forEachIndexed { index, dailySchedule ->
                dailySchedule.hoursOfOperation = openHoursList.firstOrNull { it.day == index }?.let {
                    "${it.start} - ${it.end}"
                }
            }
            notifyDataSetChanged()
        }

    private var dailySchedule = daysOfWeek.map { DailySchedule(it) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HoursViewHolder(parent)

    override fun getItemCount() = dailySchedule.size

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        holder.dailySchedule = dailySchedule[position]
    }
}