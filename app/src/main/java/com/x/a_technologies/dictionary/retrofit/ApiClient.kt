package com.x.a_technologies.dictionary.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASEURL = "https://api.dictionaryapi.dev/api/v2/entries/en/"

    fun getRetrofit(baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun apiService() : ApiService{
        return getRetrofit(BASEURL).create(ApiService::class.java)
    }
}