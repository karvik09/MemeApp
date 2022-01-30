package com.android.memeapp.constant

sealed interface LoadType
object Fresh : LoadType
data class More(
    val pageSize: Int,
    val offset: Int
) : LoadType
