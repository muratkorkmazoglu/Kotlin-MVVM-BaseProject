package com.murat.mvvm_baseproject.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.murat.mvvm_baseproject.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, "sample-db").build()
    }

}