package com.murat.mvvm_baseproject.core

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.murat.mvvm_baseproject.service.OmdbAPI
import javax.inject.Inject

open class BaseViewModel(app: Application) : RxAwareViewModel(app) {

    var progressLiveData = MutableLiveData<Boolean>()

    @Inject
    lateinit var baseApi: OmdbAPI
}

