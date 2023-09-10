package com.virtualynx.newsviewer.ui.favorite

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.virtualynx.newsviewer.dto.ApiResponseArticle
import com.virtualynx.newsviewer.entity.ArticleEntity
import com.virtualynx.newsviewer.model.ArticleModel
import com.virtualynx.newsviewer.model.SourceModel
import com.virtualynx.newsviewer.service.AppDatabase
import com.virtualynx.newsviewer.service.NewsService
import com.virtualynx.newsviewer.service.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.streams.toList

class FavoriteViewModel() : ViewModel() {
    val articles: MutableLiveData<List<ArticleModel>> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()

    fun fetch(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            val articleEntities: List<ArticleEntity> = AppDatabase.getDatabase(context).articleDao().getAll()
            articles.postValue(articleEntities.stream().map { a -> ArticleModel(
                SourceModel("", ""),
                a.author,
                a.title,
                a.description,
                a.url,
                a.urlToImage,
                null,
                a.content
            ) }.toList())
        }
    }

    fun save(context: Context, article: ArticleModel){
        viewModelScope.launch(Dispatchers.IO) {
            AppDatabase.getDatabase(context).articleDao().insertAll(
                ArticleEntity(
                    sourceId = article.source.id,
                    author = article.author,
                    content = article.content,
                    description = article.description,
                    title = article.title,
                    url = article.url,
                    urlToImage = article.urlToImage
                )
            )
        }
    }

    fun delete(context: Context, article: ArticleModel){
        viewModelScope.launch(Dispatchers.IO) {
            val dao = AppDatabase.getDatabase(context).articleDao();
            val articleEntity = dao.findByUrl(article.url)
            dao.delete(articleEntity)
        }
    }
}