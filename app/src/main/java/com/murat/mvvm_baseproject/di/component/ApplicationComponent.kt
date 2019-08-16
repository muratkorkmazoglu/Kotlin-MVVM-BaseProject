package com.murat.mvvm_baseproject.di.component

import android.content.Context
import android.content.SharedPreferences
import com.murat.mvvm_baseproject.Application
import com.murat.mvvm_baseproject.di.module.ApplicationModule
import com.murat.mvvm_baseproject.di.module.DatabaseModule
import com.murat.mvvm_baseproject.di.module.NetModule
import com.murat.mvvm_baseproject.ui.main.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton

@Component(modules = arrayOf(ApplicationModule::class, NetModule::class, DatabaseModule::class))

interface ApplicationComponent {
    fun app(): Application
    fun context(): Context
    fun preferences(): SharedPreferences
    fun inject(mainActivityViewModel: MainActivityViewModel)
}
