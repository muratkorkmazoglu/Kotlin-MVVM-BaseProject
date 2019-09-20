package com.murat.mvvm_baseproject.ui.main

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.murat.mvvm_baseproject.R
import com.murat.mvvm_baseproject.core.BaseAdapter
import com.murat.mvvm_baseproject.databinding.ListItemBinding
import com.murat.mvvm_baseproject.service.Response.Movie

class MoviesAdapter(private val callBack: (Movie, Int) -> Unit) : BaseAdapter<Movie>(movieDiffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )

        val viewModel = MainActivityViewModel((parent.context as Activity).application)

        mBinding.viewModel = viewModel

        mBinding.cardView.setOnClickListener {
            mBinding.viewModel?.movieItem?.get()?.let {
                callBack(it, mBinding.viewModel!!.position)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ListItemBinding).viewModel?.setModel(getItem(position), position)
        binding.executePendingBindings()
    }
}

val movieDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}