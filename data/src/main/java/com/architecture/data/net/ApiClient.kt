package com.architecture.data.net

import android.util.Log
import com.architecture.data.BuildConfig
import com.architecture.data.net.intercepter.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal val apiService: ApiService by lazy {
    ApiClient.INSTANCE.getApi(ApiService::class.java, "https://wanandroid.com/")
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
            .addConverterFactory(MoshiConverterFactory.create())
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
            addInterceptor(HttpLoggingInterceptor { Log.i("CoinWallet network", it) }.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
        }
        return builder
    }
}