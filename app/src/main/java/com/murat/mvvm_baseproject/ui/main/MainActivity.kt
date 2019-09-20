package com.murat.mvvm_baseproject.ui.main

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.murat.mvvm_baseproject.R
import com.murat.mvvm_baseproject.core.AppConstants
import com.murat.mvvm_baseproject.core.BaseActivity
import com.murat.mvvm_baseproject.core.Resource
import com.murat.mvvm_baseproject.databinding.ActivityMainBinding
import com.murat.mvvm_baseproject.service.Response.Movie
import com.murat.mvvm_baseproject.service.Response.TMDBResponse
import com.murat.mvvm_baseproject.utils.extensions.toast

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {

    var movieEntity: ArrayList<Movie>? = null

    override fun getLayoutRes() = R.layout.activity_main

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel

        initAdapter()
        getMovies()

        viewModel.getMovies("popular", AppConstants.NetworkService.API_KEY_VALUE, "tr", 1)

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

    private fun initAdapter() {
        val adapter =
            MoviesAdapter { item, position ->
                toast(item.title)
            }

        binding.itemsRv.layoutManager = LinearLayoutManager(applicationContext)
        binding.itemsRv.adapter = adapter
    }

    private fun getMovies() {
        viewModel.getMoviesLiveData.observe(this@MainActivity, Observer<Resource<TMDBResponse>> {
            it.let {
                movieEntity = it?.data?.results
                (binding.itemsRv.adapter as MoviesAdapter).submitList(movieEntity as List<Movie?>?)
            }
        })
    }

}
