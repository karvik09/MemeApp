package com.android.memeapp.data.model

import com.android.memeapp.db.entity.MemeObject
import com.google.gson.annotations.SerializedName

data class MemeRes(
    @SerializedName("memes")
    val memes: ArrayList<MemeObject>?
)