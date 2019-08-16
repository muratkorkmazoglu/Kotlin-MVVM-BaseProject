package com.murat.mvvm_baseproject

import com.murat.mvvm_baseproject.di.component.DaggerApplicationComponent
import com.murat.mvvm_baseproject.di.module.ApplicationModule

class Application : android.app.Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}

