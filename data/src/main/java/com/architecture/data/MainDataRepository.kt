package com.architecture.data

import android.util.Log
import com.architecture.data.mapper.toDomain
import com.architecture.domain.model.HttpResult
import com.architecture.domain.MainRepository
import com.architecture.domain.model.ArticleDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainDataRepository(private val store: Store): MainRepository{
    override suspend fun main(): Flow<HttpResult<List<ArticleDetail>>> {
        return flow {
            val articles = store.remote.getArticleList()
            val domain = articles.data.map { it.toDomain() }
            emit(HttpResult(domain))
        }
    }


}