package com.murat.mvvm_baseproject.service

import com.murat.mvvm_baseproject.core.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(with(chain.request().newBuilder()) {
            addHeader(Constants.NetworkService.API_KEY_NAME, Constants.NetworkService.API_KEY_VALUE)
            build()
        })
    }
}