package com.android.memeapp.data.network

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("data")
    val data: T?
)