package com.itworx.headlines_data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class HeadlinesDto(
    @SerializedName("articles")
    var articles: List<ArticleDto>? = listOf(),
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = 0
) : Parcelable