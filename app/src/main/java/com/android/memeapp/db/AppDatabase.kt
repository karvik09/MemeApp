package com.android.memeapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.memeapp.constant.AppConstant
import com.android.memeapp.db.dao.MemeDao
import com.android.memeapp.db.entity.MemeObject

@Database(entities = [MemeObject::class], version = AppConstant.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memeDao(): MemeDao
}