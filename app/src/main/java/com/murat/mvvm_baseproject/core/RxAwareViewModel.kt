package com.murat.mvvm_baseproject.core

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

open class RxAwareViewModel(app: Application) : AndroidViewModel(app) {
    val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }
}