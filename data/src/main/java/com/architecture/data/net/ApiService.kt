package com.architecture.data.net

import com.architecture.data.model.MainModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiService {
    @GET("/a/b")
    suspend fun getMain(@Query("") id: Int): MainModel
}