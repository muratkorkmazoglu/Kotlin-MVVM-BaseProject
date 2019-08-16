package com.murat.mvvm_baseproject.core

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<ViewModel : BaseViewModel, DataBinding : ViewDataBinding>(private val mViewModelClass: Class<ViewModel>) :
    AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DataBinding
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)
        super.onCreate(savedInstanceState)
        onInject()
    }

    /* If you want to inject Dependency Injection
     on your activity, you can override this.*/
    open fun onInject() {}

    abstract fun initViewModel(viewModel: ViewModel)

}