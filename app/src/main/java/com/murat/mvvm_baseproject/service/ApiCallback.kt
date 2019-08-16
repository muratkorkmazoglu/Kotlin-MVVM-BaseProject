package com.murat.mvvm_baseproject.service

import com.murat.mvvm_baseproject.core.BaseCallBack

class ApiCallback<T>(baseCallBack: BaseCallBack<T>?) : BaseRetrofitCallback<T>(baseCallBack)
