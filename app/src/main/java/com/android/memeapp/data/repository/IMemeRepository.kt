package com.android.memeapp.data.repository

import com.android.memeapp.data.model.MemeRes
import com.android.memeapp.db.entity.MemeObject
import com.android.memeapp.data.network.Resource
import com.android.memeapp.data.network.Response

interface IMemeRepository {
    suspend fun fetchFromRemote(): Resource<ArrayList<MemeObject>>
    suspend fun fetchFromDB(pageSize:Int,pageOffset:Int): Resource<ArrayList<MemeObject>>
}