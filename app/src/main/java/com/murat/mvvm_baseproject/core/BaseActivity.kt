package com.murat.mvvm_baseproject.core

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.murat.mvvm_baseproject.utils.AppProgress

abstract class BaseActivity<ViewModel : BaseViewModel, DataBinding : ViewDataBinding>(private val mViewModelClass: Class<ViewModel>) :
    AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    var dialog: Dialog? = null

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
    }

    /* If you want to inject Dependency Injection
     on your activity, you can override this.*/
    open fun onInject() {}

    abstract fun initViewModel(viewModel: ViewModel)

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