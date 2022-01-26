package com.architecture.domain

import com.architecture.domain.model.ArticleDetail
import com.architecture.domain.model.HttpResult
import com.architecture.domain.model.Main
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun main(): Flow<HttpResult<List<ArticleDetail>>>
}