package com.rubenshardt.yelpgettycenter.modules

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rubenshardt.yelpgettycenter.R
import kotlinx.android.synthetic.main.activity_business_details.*

class BusinessDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_details)
        setSupportActionBar(toolbar)

    }
}
