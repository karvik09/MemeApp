package com.android.memeapp.data.repository.source.network

import com.android.memeapp.data.model.MemeRes
import com.android.memeapp.data.network.Resource
import com.android.memeapp.data.network.Response

interface INetworkSource {
    suspend fun fetchMeme(): Response<MemeRes>

}