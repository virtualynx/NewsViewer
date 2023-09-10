package com.virtualynx.newsviewer.ui.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virtualynx.newsviewer.dto.ApiResponseArticle
import com.virtualynx.newsviewer.model.ArticleModel
import com.virtualynx.newsviewer.model.SourceModel
import com.virtualynx.newsviewer.service.NewsService
import com.virtualynx.newsviewer.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.streams.toList

class ArticleViewModel : ViewModel() {
    val articles: MutableLiveData<List<ArticleModel>> = MutableLiveData()

    fun fetch(source: String){
        val client = ServiceBuilder.buildService(NewsService::class.java).getArticlesBySources(source)

        client.enqueue(object : Callback<ApiResponseArticle> {

            override fun onResponse(
                call: Call<ApiResponseArticle>,
                response: Response<ApiResponseArticle>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    error("API call error: ${response.code()}")
                    return
                }

                val models: List<ArticleModel> = responseBody.articles.stream().map{a ->
                    ArticleModel(
                        SourceModel(a.source.id, a.source.name),
                        a.author,
                        a.title,
                        a.description,
                        a.url,
                        a.urlToImage,
                        a.publishedAt,
                        a.content
                    )
                }.toList()

                articles.postValue(models)
            }

            override fun onFailure(call: Call<ApiResponseArticle>, t: Throwable) {
                t.printStackTrace()

                articles.postValue(listOf())
            }

        })
    }
}