package com.murat.mvvm_baseproject.utils.extensions

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.widget.Toast

fun Activity.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()

inline fun Activity.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder): AlertDialog {
    return AlertDialog.Builder(this)
            .body()
            .show()
}