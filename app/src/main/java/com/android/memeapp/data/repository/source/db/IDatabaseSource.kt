package com.android.memeapp.data.repository.source.db

import com.android.memeapp.data.network.Resource
import com.android.memeapp.db.entity.MemeObject

interface IDatabaseSource {

    suspend fun fetchMeme(pageSize: Int, pageOffset: Int)
            : ArrayList<MemeObject>
    suspend fun saveMeme(meme:ArrayList<MemeObject>?)
}