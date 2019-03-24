package com.rubenshardt.yelpgettycenter.modules.base

import android.support.v7.app.AppCompatActivity
import com.rubenshardt.yelpgettycenter.R
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

    protected fun animateRightToLeft() {
        overridePendingTransition(R.anim.in_right_to_left, R.anim.out_right_to_left)
    }

    protected fun animateLeftToRight() {
        overridePendingTransition(R.anim.in_left_to_right, R.anim.out_left_to_right)
    }

    protected fun animateFadeInFadeOut() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}