package com.virtualynx.newsviewer.dto

import java.util.Date

data class ApiResponseArticle(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
){
    data class Article(
        val source: ApiResponseSource.Source,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: Date,
        val content: String
    )
}
