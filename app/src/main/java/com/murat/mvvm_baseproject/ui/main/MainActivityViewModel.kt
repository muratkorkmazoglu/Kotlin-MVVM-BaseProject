package com.murat.mvvm_baseproject.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.murat.mvvm_baseproject.core.*
import com.murat.mvvm_baseproject.service.Response.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    val getMoviesLiveData = MutableLiveData<Resource<MovieResponse>>()

    init {
        (app as? com.murat.mvvm_baseproject.Application)?.component?.inject(this)
    }

    fun getMovies(tile: String, year: String) {
        disposable.add(baseApi.getSearchMovies(tile, year, AppConstants.NetworkService.API_KEY_VALUE)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getMoviesLiveData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }
}