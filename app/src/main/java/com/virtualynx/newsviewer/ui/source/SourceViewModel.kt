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

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is gallery Fragment"
//    }
//    val text: LiveData<String> = _text

    val sources: MutableLiveData<List<SourceModel>> = MutableLiveData()

    fun fetchSources(){
        val client = ServiceBuilder.buildService(NewsService::class.java).getSources()

        client.enqueue(object : Callback<ApiResponseSource> {

            override fun onResponse(
                call: Call<ApiResponseSource>,
                response: Response<ApiResponseSource>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
//                    onError("Data Processing Error")
                    return
                }

//                val sourceList: List<String> = responseBody.sources.stream()
//                    .filter { source -> source.id == "engadget" }
//                    .map(a -> new Source())
//                    .toList()

                val sourceList: List<ApiResponseSource.Source> = responseBody.sources

//                val sourceModels: ArrayList<Source> = arrayListOf()

//                val sourceModels: List<Source> = sourceList.stream().map{a -> Source(a.id, a.name)}.toList()
                val sourceModelModels: List<SourceModel> = responseBody.sources.stream().map{ a -> SourceModel(a.id, a.name)}.toList()

//                sourceList.forEach {
//                    sourceModels.add(Source(it.id, it.name))
//                }

//                _isLoading.value = false
                sources.postValue(sourceModelModels)

            }

            override fun onFailure(call: Call<ApiResponseSource>, t: Throwable) {
//                onError(t.message)
                t.printStackTrace()

                val sourceModelModels: ArrayList<SourceModel> = arrayListOf()
                sources.postValue(sourceModelModels)
            }

        })
    }
}