package com.virtualynx.newsviewer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ArticleEntity(
    val sourceId: String,
    val author: String?,
    val title: String?,
    val description: String?,

    @PrimaryKey
    val url: String,

    val urlToImage: String?,
    val content: String?
)
