package com.virtualynx.newsviewer.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Article(
    @SerializedName("source")
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String
)
