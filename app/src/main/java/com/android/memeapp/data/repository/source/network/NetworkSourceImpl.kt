package com.android.memeapp.data.repository.source.network

import com.android.memeapp.data.model.MemeRes
import com.android.memeapp.data.network.ApiService
import com.android.memeapp.data.network.Response
import javax.inject.Inject

class NetworkSourceImpl @Inject constructor(
    private val apiService: ApiService
) : INetworkSource {
    override suspend fun fetchMeme(): Response<MemeRes> {
        return apiService.getMemes()
    }
}