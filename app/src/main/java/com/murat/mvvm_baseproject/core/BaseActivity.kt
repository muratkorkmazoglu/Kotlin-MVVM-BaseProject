package com.murat.mvvm_baseproject.core

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.IntentFilter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.murat.mvvm_baseproject.R
import com.murat.mvvm_baseproject.utils.AppProgress
import com.murat.mvvm_baseproject.utils.ConnectivityReceiver

abstract class BaseActivity<ViewModel : BaseViewModel, DataBinding : ViewDataBinding>(private val mViewModelClass: Class<ViewModel>) :
    AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    var dialog: Dialog? = null
    private var snackBar: Snackbar? = null
    private val connectivityReceiver = ConnectivityReceiver()

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DataBinding
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)
        super.onCreate(savedInstanceState)
        initDialog()
        onInject()
        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    /* If you want to inject Dependency Injection
     on your activity, you can override this.*/
    open fun onInject() {}

    abstract fun initViewModel(viewModel: ViewModel)

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar =
                Snackbar.make(findViewById(R.id.rootView), R.string.networkConnectionMessage, Snackbar.LENGTH_LONG)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.view?.setBackgroundColor(Color.parseColor("#F48B8C"))
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            snackBar?.show()
        } else {
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            snackBar?.dismiss()
        }
    }

    private fun initDialog() {
        dialog = AppProgress.progressDialog(this)
    }

    fun isShow(): Boolean? {
        return dialog?.isShowing
    }

    fun showProgress() {
        runOnUiThread {
            dialog?.show()
        }
    }

    fun hideProgress() {
        runOnUiThread {
            dialog?.dismiss()
        }
    }

}