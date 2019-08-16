package com.murat.mvvm_baseproject.ui.main

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.murat.mvvm_baseproject.R
import com.murat.mvvm_baseproject.core.BaseActivity
import com.murat.mvvm_baseproject.core.Resource
import com.murat.mvvm_baseproject.databinding.ActivityMainBinding
import com.murat.mvvm_baseproject.service.Response.MovieResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
        getMovies()

        if (viewModel.progressLiveData.hasActiveObservers())
            viewModel.progressLiveData.removeObservers(this)

        viewModel.progressLiveData.observe(this@MainActivity, Observer<Boolean> {
            it?.let {
                if (it)
                    showProgress()
                else
                    hideProgress()
            }
        })
    }

    override fun getLayoutRes() = R.layout.activity_main

    private fun getMovies() {
        viewModel.getMovies("Avengers", "2015")
        if (viewModel.getMoviesLiveData.hasActiveObservers())
            viewModel.getMoviesLiveData.removeObservers(this)

        viewModel.getMoviesLiveData.observe(this@MainActivity, Observer<Resource<MovieResponse>> {
            it.let {
                titleTv.text = it?.data?.title
            }

        })
    }
}
