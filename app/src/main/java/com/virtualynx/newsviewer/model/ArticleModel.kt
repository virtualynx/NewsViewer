package com.virtualynx.newsviewer.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticleModel(
    @SerializedName("source")
    val source: SourceModel,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: Date?,
    val content: String?
)
