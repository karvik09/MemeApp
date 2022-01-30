package com.android.memeapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meme")
data class MemeObject(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,
    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,
    @SerializedName("width")
    @ColumnInfo(name = "width")
    val width: Float?,
    @SerializedName("height")
    @ColumnInfo(name = "height")
    val height: Float?,
    @SerializedName("box_count")
    @ColumnInfo(name = "box_count")
    val boxCount: Int?
)