package com.architecture.data

import com.architecture.data.model.ApiResponse
import com.architecture.data.model.ArticleDetail


class SignRepository(private val store: Store): BaseRepository() {
    suspend fun login(email: String, password: String): ApiResponse<List<ArticleDetail>> {
        return executeHttp {
            store.remote.getArticleList()
        }
    }

    suspend fun register() {
    }

    suspend fun verifyCode() {
    }
}