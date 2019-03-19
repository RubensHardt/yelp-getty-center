package com.rubenshardt.yelpgettycenter.modules.businessdetails

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.modules.base.BaseActivity
import com.rubenshardt.yelpgettycenter.modules.hoursofoperation.HoursOfOperationActivity
import com.rubenshardt.yelpgettycenter.modules.webview.WebViewActivity
import com.rubenshardt.yelpgettycenter.utils.adapters.BusinessDetailsAdapter
import com.rubenshardt.yelpgettycenter.utils.constants.IntentConstants.TITLE
import com.rubenshardt.yelpgettycenter.utils.constants.IntentConstants.URL
import com.rubenshardt.yelpgettycenter.utils.extensions.getViewModel
import com.rubenshardt.yelpgettycenter.utils.extensions.loadCroppedImage
import com.rubenshardt.yelpgettycenter.utils.listeners.BusinessInfoListener
import com.rubenshardt.yelpgettycenter.utils.listeners.MapListener
import com.rubenshardt.yelpgettycenter.utils.listeners.PhotosListener
import kotlinx.android.synthetic.main.activity_business_details.*

class BusinessDetailsActivity : BaseActivity(), BusinessInfoListener, PhotosListener, MapListener {

    private lateinit var viewModel: BusinessDetailsInterface
    private val adapter = BusinessDetailsAdapter(this, this, this)

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
        contentRecyclerView.adapter = adapter
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
                businessCoverImageView.loadCroppedImage(it.imageUrl)
                adapter.business = it
            }
        })

        viewModel.reviewsLiveData.observe(this, Observer { reviews ->
            reviews?.let { adapter.reviews = it }
        })

        viewModel.coordinatesLiveEvent.observe(this, Observer { coordinates ->
            val latitude = coordinates?.latitude
            val longitude = coordinates?.longitude
            val uri = "geo:$latitude,$longitude"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        })

        viewModel.hoursOfOperationLiveEvent.observe(this, Observer {
        })

        viewModel.phoneNumberLiveEvent.observe(this, Observer { phoneNumber ->
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        })


        viewModel.externalUrlLiveEvent.observe(this, Observer { url ->
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(intent)
        })

        viewModel.photoUrlLiveEvent.observe(this, Observer { url ->
        })
    }

    // region MapListener

    override fun onMapClicked(coordinates: Coordinates) {
        viewModel.onMapClicked(coordinates)
    }

    // endregion

    // region BusinessInfoListener

    override fun onHoursOfOperationClicked() {
        viewModel.onHoursOfOperationClicked()
    }

    override fun onCallClicked(phoneNumber: String) {
        viewModel.onCallClicked(phoneNumber)
    }

    override fun onVisitWebsiteClicked(url: String) {
        viewModel.onVisitWebsiteClicked(url)
    }

    // endregion

    // region PhotosListener

    override fun onPhotoClicked(url: String) {
        viewModel.onPhotoClicked(url)
    }

    // endregion
}
