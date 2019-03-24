package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import com.rubenshardt.yelpgettycenter.utils.listeners.BusinessInfoListener
import kotlinx.android.synthetic.main.item_business_info.view.*

class BusinessInfoViewHolder(parent: ViewGroup, private val listener: BusinessInfoListener) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.item_business_info)) {

    init {
        with(itemView) {
            hoursOfOperationInfoView.setOnClickListener {
                listener.onHoursOfOperationClicked()
            }
            callContactInfoView.setOnClickListener {
                business?.phone?.let { listener.onCallClicked(it) }
            }
            openWebsiteContactInfoView.setOnClickListener {
                business?.url?.let { listener.onVisitWebsiteClicked(it) }
            }
        }
    }

    var business: Business? = null
        set(value) {
            field = value
            with(itemView) {
                hoursOfOperationInfoView.setup(context.getString(R.string.hours_of_operation), R.drawable.ic_clock)
                callContactInfoView.setup(
                    context.getString(R.string.call_business, value?.displayPhone),
                    R.drawable.ic_call
                )
                openWebsiteContactInfoView.setup(context.getString(R.string.visit_website), R.drawable.ic_open_website)
            }
        }
}