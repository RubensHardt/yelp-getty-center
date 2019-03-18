package com.rubenshardt.yelpgettycenter.modules.base

import android.support.v7.app.AppCompatActivity
import com.rubenshardt.yelpgettycenter.utils.dialogs.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    fun showLoadingDialog(cancellable: Boolean = false, cancelCallback: ((cancelled: Boolean) -> Unit)? = null) {
        loadingDialog?.dismiss()
        loadingDialog = LoadingDialog.show(this, null, cancellable, cancelCallback)
    }

    fun dismissLoadingDialog() {
        loadingDialog?.dismiss()
    }
}