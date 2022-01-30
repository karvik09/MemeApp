package com.android.memeapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.memeapp.db.entity.MemeObject

@Dao
interface MemeDao {

    @Query("SELECT * from meme LIMIT :pageSize OFFSET :offset")
    fun fetchMeme(pageSize: Int, offset: Int): List<MemeObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(meme: List<MemeObject>)
}