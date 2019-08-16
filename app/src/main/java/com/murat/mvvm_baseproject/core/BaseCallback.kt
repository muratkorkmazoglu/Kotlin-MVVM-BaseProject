package com.murat.mvvm_baseproject.core

interface BaseCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(message: String)
}