package com.murat.mvvm_baseproject.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.murat.mvvm_baseproject.core.*
import com.murat.mvvm_baseproject.service.Response.Movie
import com.murat.mvvm_baseproject.service.Response.TMDBResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    val getMoviesLiveData = MutableLiveData<Resource<TMDBResponse>>()
    var movieItem = ObservableField<Movie>()
    var position = -1

    init {
        (app as? com.murat.mvvm_baseproject.Application)?.component?.inject(this)
    }

    fun setModel(item: Movie, position: Int) {
        this.movieItem.set(item)
        this.position = position
    }

    fun getMovies(type: String, api_key: String, language: String, page: Int) {
        disposable.add(baseApi.getMovies(type, api_key, language,page)
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