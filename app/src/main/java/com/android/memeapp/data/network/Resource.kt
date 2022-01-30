package com.android.memeapp.data.network

import com.android.memeapp.constant.LoadType

sealed interface Resource<T>
data class Success<T>(val body: T?) : Resource<T>
data class Loading<T>(val loadType: LoadType) : Resource<T>
class Failure<T> : Resource<T>