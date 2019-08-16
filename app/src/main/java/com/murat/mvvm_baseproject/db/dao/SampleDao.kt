package com.murat.mvvm_baseproject.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.murat.mvvm_baseproject.db.entitiy.Sample

@Dao
interface SampleDao {

    @Query("SELECT * FROM Sample")
    fun getSamples(): LiveData<List<Sample>>

    @Query("SELECT * FROM Sample where id = :exampleId")
    fun getSample(exampleId: Long): Sample

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSample(example: Sample)

    @Update
    fun updateSample(example: Sample)

    @Delete
    fun deleteSample(example: Sample)

    @Query("Select count(*) from Sample")
    fun getCount(): Int

}