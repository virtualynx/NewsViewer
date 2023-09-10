package com.virtualynx.newsviewer.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object ServiceBuilder {
//        public const val API_KEY = "f565c7bc7aa34aa6b908850e698ae574"
        public const val API_KEY = "9f63bd7c7ee6415fb67203d33cc79244"

        private const val URL = "https://newsapi.org/v2/"

        //CREATE HTTP CLIENT
        private val okHttp = OkHttpClient.Builder()

        //retrofit builder
        private val retrofitBuilder = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

        private val retrofit = retrofitBuilder.build()

        //we will use this class to create an anonymous inner class function that
        //implements Country service Interface

        fun <T> buildService(serviceType: Class<T>): T {
            return retrofit.create(serviceType)
        }
    }
}