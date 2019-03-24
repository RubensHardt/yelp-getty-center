package com.rubenshardt.yelpgettycenter.modules.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.modules.base.BaseActivity
import com.rubenshardt.yelpgettycenter.utils.constants.IntentConstants.TITLE
import com.rubenshardt.yelpgettycenter.utils.constants.IntentConstants.URL
import kotlinx.android.synthetic.main.activity_business_details.*
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animateFadeInFadeOut()
        setContentView(R.layout.activity_webview)
        setupView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        animateFadeInFadeOut()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                animateFadeInFadeOut()
            }
            else -> {
            }
        }
        return true
    }

    private fun setupView() {
        title = intent.getStringExtra(TITLE)
        intent.getStringExtra(URL)?.let(::setupUrl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupUrl(url: String) {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressIndicator.visibility = VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressIndicator.visibility = GONE
                super.onPageFinished(view, url)
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.displayZoomControls = false
        webView.loadUrl(url)
    }
}