package com.rubenshardt.yelpgettycenter.modules.hoursofoperation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.MenuItem
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.modules.base.BaseActivity
import com.rubenshardt.yelpgettycenter.utils.adapters.HoursAdapter
import com.rubenshardt.yelpgettycenter.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_hours_of_operation.*

class HoursOfOperationActivity : BaseActivity() {

    private lateinit var viewModel: HoursOfOperationInterface
    private lateinit var adapter: HoursAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animateRightToLeft()
        setContentView(R.layout.activity_hours_of_operation)
        viewModel = getViewModel<HoursOfOperationViewModel>()
        setupView()
        setupObservers()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        animateLeftToRight()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                animateLeftToRight()
            }
            else -> {
            }
        }
        return true
    }

    private fun setupView() {
        adapter = HoursAdapter(resources.getStringArray(R.array.week_days))
        contentRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.businessLiveData.observe(this, Observer { business ->
            business?.hours?.firstOrNull()?.open?.let { openHours ->
                adapter.openHoursList = openHours
            }
        })
    }
}