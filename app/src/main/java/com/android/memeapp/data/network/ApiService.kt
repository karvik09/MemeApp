package com.android.memeapp.data.network

import com.android.memeapp.data.model.MemeRes
import retrofit2.http.GET

interface ApiService {

    @GET("/get_memes")
    suspend fun getMemes(): Response<MemeRes>
}