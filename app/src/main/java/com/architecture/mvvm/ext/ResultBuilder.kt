package com.architecture.mvvm.ext

import com.architecture.data.model.ApiEmptyResponse
import com.architecture.data.model.ApiErrorResponse
import com.architecture.data.model.ApiFailedResponse
import com.architecture.data.model.ApiResponse
import com.architecture.data.model.ApiSuccessResponse


fun <T> ApiResponse<T>.parseData(listenerBuilder: ResultBuilder<T>.() -> Unit) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.response)
        is ApiEmptyResponse -> listener.onDataEmpty()
        is ApiFailedResponse -> listener.onFailed(this.errorCode, this.errorMsg)
        is ApiErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}

class ResultBuilder<T> {
    var onSuccess: (data: T?) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, errorMsg ->
        errorMsg?.let {  }
    }
    var onError: (e: Throwable) -> Unit = { e ->
        e.message?.let {  }
    }
    var onComplete: () -> Unit = {}
    fun onLoading(): Unit {}
}