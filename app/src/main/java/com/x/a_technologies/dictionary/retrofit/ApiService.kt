package com.x.a_technologies.dictionary.retrofit

import com.x.a_technologies.dictionary.models.MyWord
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{word}")
    suspend fun getMyWord(@Path("word") word:String): List<MyWord>
}