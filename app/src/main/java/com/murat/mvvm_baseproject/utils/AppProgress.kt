package com.murat.mvvm_baseproject.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.mikhaellopez.rxanimation.RxAnimation
import com.mikhaellopez.rxanimation.rotation
import com.murat.mvvm_baseproject.R
import com.murat.mvvm_baseproject.core.AppConstants
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.progress_dialog.*

class AppProgress {
    companion object {
        fun progressDialog(context: Context): Dialog {
            val composite = CompositeDisposable()
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(false)

            val logo = dialog.imageViewAppLogo

            RxAnimation.sequentially(
                logo.rotation(360f, AppConstants.MainAnimationConstants.SHORT_DURATION),
                logo.rotation(0f, AppConstants.MainAnimationConstants.NO_DURATION))
                .repeat()
                .subscribe().addTo(composite)

            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            return dialog
        }
    }
}