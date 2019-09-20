package com.murat.mvvm_baseproject.utils.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.widget.Toast

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(message, duration)

inline fun Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) = activity?.alertDialog(body)
