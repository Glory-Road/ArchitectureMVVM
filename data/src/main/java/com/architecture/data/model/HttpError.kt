package com.architecture.data.model

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

enum class HttpError(var code: Int, var errorMsg: String) {
    TOKEN_EXPIRE(3001, "token is expired"),
    PARAMS_ERROR(4003, "params is error")
    // ...... more
}

internal fun handlingApiExceptions(code: Int?, errorMsg: String?) = when (code) {
    HttpError.TOKEN_EXPIRE.code -> {
        HttpError.TOKEN_EXPIRE.errorMsg
    }
    HttpError.PARAMS_ERROR.code -> {
        HttpError.PARAMS_ERROR.errorMsg
    }
    else -> errorMsg?.let {  }
}

internal fun handlingExceptions(e: Throwable) = when (e) {
    is HttpException -> {

    }
    is CancellationException -> {
    }
    is SocketTimeoutException -> {
    }
    else -> {
    }
}