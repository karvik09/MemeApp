package com.android.memeapp.data.repository.source.db

import com.android.memeapp.db.AppDatabase
import com.android.memeapp.db.entity.MemeObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseSourceImpl @Inject constructor(
    private val db: AppDatabase
) : IDatabaseSource {

    override suspend fun fetchMeme(
        pageSize: Int,
        pageOffset: Int
    ): ArrayList<MemeObject> {
        return withContext(Dispatchers.IO) {
            val list = db.memeDao().fetchMeme(pageSize, pageOffset)
            ArrayList(list)
        }
    }

    override suspend fun saveMeme(meme: ArrayList<MemeObject>?) {
        withContext(Dispatchers.IO) {
            meme?.let {
                db.memeDao().insertAll(it.toList())
            }
        }
    }
}