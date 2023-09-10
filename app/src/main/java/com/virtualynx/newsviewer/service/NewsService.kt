package com.virtualynx.newsviewer.service

import com.virtualynx.newsviewer.dto.ApiResponseArticle
import com.virtualynx.newsviewer.dto.ApiResponseSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/sources")
    fun getSources (
        @Query("category") category: String = "",
        @Query("apiKey") key: String = ServiceBuilder.API_KEY,
    ) : Call<ApiResponseSource>

    @GET("everything")
    fun getArticlesBySources (
        @Query("sources") sources: String,
        @Query("q") q: String = "",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") key: String = ServiceBuilder.API_KEY,
    ) : Call<ApiResponseArticle>
}