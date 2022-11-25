package com.itworx.headlines_data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class HeadlinesDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>? = listOf(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = 0
) : Parcelable