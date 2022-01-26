package com.architecture.data.net

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   :
 */
class RemoteProvider() {
    suspend fun getArticleList() = apiService.getArticleList()
}