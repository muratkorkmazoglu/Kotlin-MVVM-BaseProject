package com.murat.mvvm_baseproject.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.murat.mvvm_baseproject.db.dao.SampleDao
import com.murat.mvvm_baseproject.db.entitiy.Sample

@Database(entities = arrayOf(Sample::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}