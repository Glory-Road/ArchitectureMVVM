package com.architecture.data

import android.content.Context
import com.architecture.data.cache.CacheProvider
import com.architecture.data.net.ApiClient
import com.architecture.data.net.RemoteProvider

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   :
 */
class Store(
    context: Context,
    baseUrl: String
) {
    private val cacheProvider = CacheProvider()
    private val remoteProvider = RemoteProvider()
    internal val remote: RemoteProvider
        get() = remoteProvider
    internal val cache: CacheProvider
        get() = cacheProvider
}