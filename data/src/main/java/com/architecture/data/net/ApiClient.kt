package com.architecture.data.net

import com.architecture.data.net.intercepter.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal val apiService: ApiService by lazy {
    ApiClient.INSTANCE.getApi(ApiService::class.java, "")
}
class ApiClient {
    companion object {
        val INSTANCE: ApiClient by lazy {
            ApiClient()
        }
    }

    fun <T> getApi(serviceClass: Class<T>, baseUrl: String): T {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
        return setRetrofitBuilder(retrofitBuilder).build().create(serviceClass)
    }

    private val okHttpClient: OkHttpClient
        get() {
            var builder = OkHttpClient.Builder()
            builder = setHttpClientBuilder(builder)
            return builder.build()
        }

    private fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder
    }

    private fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            addInterceptor(LoggingInterceptor())
        }
        return builder
    }
}