package com.architecture.data.net

import com.architecture.data.model.ApiResponse
import com.architecture.data.model.ApiSuccessResponse
import com.architecture.data.model.ArticleDetail
import kotlinx.coroutines.delay

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   :
 */
class RemoteProvider() {
    suspend fun getArticleList() = apiService.getArticleList()

//    suspend fun login(email: String, password: String): ApiResponse<List<ArticleDetail>> {
//        return apiService.getArticleList()
//    }
}