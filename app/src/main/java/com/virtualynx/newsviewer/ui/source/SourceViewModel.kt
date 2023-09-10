package com.virtualynx.newsviewer.ui.source

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virtualynx.newsviewer.dto.ApiResponseSource
import com.virtualynx.newsviewer.model.SourceModel
import com.virtualynx.newsviewer.service.NewsService
import com.virtualynx.newsviewer.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.streams.toList

class SourceViewModel : ViewModel() {
    val sources: MutableLiveData<List<SourceModel>> = MutableLiveData()

    fun fetch(category: String? = null){
        var client:  Call<ApiResponseSource>

        if(category.isNullOrEmpty()){
            client = ServiceBuilder.buildService(NewsService::class.java).getSources()
        }else{
            client = ServiceBuilder.buildService(NewsService::class.java).getSources(category)
        }

        client.enqueue(object : Callback<ApiResponseSource> {

            override fun onResponse(
                call: Call<ApiResponseSource>,
                response: Response<ApiResponseSource>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    error("API call error: ${response.code()}")
                    return
                }

                val sourceList: List<ApiResponseSource.Source> = responseBody.sources

                val sourceModelModels: List<SourceModel> = sourceList.stream().map{a -> SourceModel(a.id, a.name)}.toList()

                sources.postValue(sourceModelModels)
            }

            override fun onFailure(call: Call<ApiResponseSource>, t: Throwable) {
                t.printStackTrace()

                sources.postValue(listOf())
            }

        })
    }
}