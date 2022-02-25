package com.architecture.data

import com.architecture.data.model.ApiEmptyResponse
import com.architecture.data.model.ApiErrorResponse
import com.architecture.data.model.ApiFailedResponse
import com.architecture.data.model.ApiResponse
import com.architecture.data.model.ApiSuccessResponse
import com.architecture.data.model.HttpResultModel
import com.architecture.data.model.handlingApiExceptions
import kotlinx.coroutines.delay

open class BaseRepository {

    suspend fun <T> executeHttp(block: suspend () -> HttpResultModel<T>): ApiResponse<T> {
        //for test
        delay(500)
        runCatching {
            block.invoke()
        }.onSuccess { data: HttpResultModel<T> ->
            return handleHttpOk(data)
        }.onFailure { e ->
            return handleHttpError(e)
        }
        return ApiEmptyResponse()
    }

    /**
     * 非后台返回错误，捕获到的异常
     */
    private fun <T> handleHttpError(e: Throwable): ApiErrorResponse<T> {
        if (BuildConfig.DEBUG) e.printStackTrace()
//        handlingExceptions(e)
        return ApiErrorResponse(e)
    }

    /**
     * 返回200，但是还要判断isSuccess
     */
    private fun <T> handleHttpOk(data: HttpResultModel<T>): ApiResponse<T> {
        return if (data.errorCode == 0) {
            val reop = ApiResponse(data.data, data.errorCode, data.errorMsg)
            getHttpSuccessResponse(reop)
        } else {
            handlingApiExceptions(data.errorCode, data.errorMsg)
            ApiFailedResponse(data.errorCode, data.errorMsg)
        }
    }

    /**
     * 成功和数据为空的处理
     */
    private fun <T> getHttpSuccessResponse(response: ApiResponse<T>): ApiResponse<T> {
        val data = response.data
        return if (data == null || data is List<*> && (data as List<*>).isEmpty()) {
            ApiEmptyResponse()
        } else {
            ApiSuccessResponse(data)
        }
    }

}