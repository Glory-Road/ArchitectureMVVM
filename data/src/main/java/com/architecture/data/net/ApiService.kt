package com.architecture.data.net

import com.architecture.data.model.ApiResponse
import com.architecture.data.model.ArticleDetail
import com.architecture.data.model.HttpResultModel
import retrofit2.http.GET

internal interface ApiService {

    @GET("article/top/json")
    suspend fun getArticleList(): HttpResultModel<List<ArticleDetail>>

    @GET("article/top/json")
    suspend fun login(email: String, password: String): ApiResponse<Unit>
}