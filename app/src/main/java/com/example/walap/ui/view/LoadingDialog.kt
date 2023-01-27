package com.example.walap.ui.view

import android.app.Activity
import android.app.AlertDialog
import com.example.walap.R

class LoadingDialog(val mActivity: Activity) {
    private var isdialog: AlertDialog? = null
    fun startLoading(){
        /**set View*/
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_alert,null)
        /**set Dialog*/
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog?.show()
    }
    fun isDismiss(){
        isdialog?.dismiss()
    }
}