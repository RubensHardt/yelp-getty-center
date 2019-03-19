package com.rubenshardt.yelpgettycenter.modules.businessdetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.modules.base.BaseActivity
import com.rubenshardt.yelpgettycenter.utils.extensions.getViewModel
import com.rubenshardt.yelpgettycenter.utils.extensions.loadCroppedImage
import kotlinx.android.synthetic.main.activity_business_details.*

class BusinessDetailsActivity : BaseActivity() {

    private lateinit var viewModel: BusinessDetailsInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_details)
        setSupportActionBar(toolbar)
        viewModel = getViewModel<BusinessDetailsViewModel>()
        setupView()
        setupObservers()
        viewModel.refreshBusinessDetails()
    }

    private fun setupView() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshBusinessDetails()
        }
    }

    private fun setupObservers() {
        viewModel.loadingLiveData.observe(this, Observer { isLoading ->
            if (isLoading == true) {
                showLoadingDialog()
            } else {
                dismissLoadingDialog()
            }
        })

        viewModel.errorLiveEvent.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Log.e(toString(), it)
                Snackbar.make(activityContent, getString(R.string.api_error), Snackbar.LENGTH_LONG).show()
            }
        })

        viewModel.refreshingLiveData.observe(this, Observer { isRefreshing ->
            swipeRefreshLayout.isRefreshing = isRefreshing ?: false
        })

        viewModel.businessLiveData.observe(this, Observer { business ->
            business?.let {
                //TODO: update adapter
                businessCoverImageView.loadCroppedImage(it.imageUrl)
            }
        })

        viewModel.reviewsLiveData.observe(this, Observer { reviews ->
            //TODO: update adapter
        })
    }
}
