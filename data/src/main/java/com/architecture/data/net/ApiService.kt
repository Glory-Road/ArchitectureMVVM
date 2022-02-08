package com.architecture.data.net

import com.architecture.data.model.ArticleDetailModel
import com.architecture.data.model.HttpResultModel
import retrofit2.http.GET

internal interface ApiService {

    @GET("article/top/json")
    suspend fun getArticleList(): HttpResultModel<List<ArticleDetailModel>>
}