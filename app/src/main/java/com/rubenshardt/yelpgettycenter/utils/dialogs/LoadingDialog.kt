package com.rubenshardt.yelpgettycenter.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.rubenshardt.yelpgettycenter.R
import kotlinx.android.synthetic.main.view_loading_dialog.*

class LoadingDialog(context: Context): Dialog(context, R.style.LoadingDialogStyle) {
    companion object {
        fun show(context: Context, message: String?, isCancelable: Boolean = true, listener: ((cancelled: Boolean) -> Unit)?): LoadingDialog {
            val dialog = LoadingDialog(context)
            dialog.setContentView(R.layout.view_loading_dialog)
            dialog.setCancelable(isCancelable)
            dialog.setOnCancelListener { listener?.invoke(true) }
            dialog.setOnShowListener {
                dialog.loadingProgressBar?.indeterminateDrawable?.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY)
                dialog.loadingMessageTextView?.text = message

                val windowLayoutParams = WindowManager.LayoutParams()
                windowLayoutParams.copyFrom(dialog.window?.attributes)
                windowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
                windowLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                dialog.window?.attributes = windowLayoutParams
            }
            dialog.show()

            val window = dialog.window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window?.setDimAmount(0.7f)

            return dialog
        }
    }
}