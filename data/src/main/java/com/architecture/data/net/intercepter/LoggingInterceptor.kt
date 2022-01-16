package com.architecture.data.net.intercepter

import com.architecture.data.extension.logV
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        logV(TAG, "Sending request: ${request.url} \n ${request.headers}")
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        logV(TAG, "Received response for ${response.request.url} in ${(t2 - t1) / 1e6} ms\n${response.headers}")
        return response
    }

    companion object{
        const val TAG = "LoggingIntercept"
    }
}