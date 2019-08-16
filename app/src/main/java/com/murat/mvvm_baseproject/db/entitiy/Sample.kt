package com.murat.mvvm_baseproject.db.entitiy

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity()
class Sample {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var exampleString: String = ""
}