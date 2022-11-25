package com.itworx.headlines_data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SourceDto(
    @SerializedName("id")
    val id: String? = "", // cnn
    @SerializedName("name")
    val name: String? = "" // CBS Sports
) : Parcelable