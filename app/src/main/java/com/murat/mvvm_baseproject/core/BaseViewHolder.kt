package com.murat.mvvm_baseproject.core

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

open class BaseViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)