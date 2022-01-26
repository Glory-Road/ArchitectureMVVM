package com.architecture.base

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   : 页面状态
 */
sealed class ComponentState<T>(
    val data:T? = null,
    val errorCode: Int? = null,
    val errorMsg: String? = null
) {
    class Success<T>(data: T): ComponentState<T>(data)
    class Loading<T>(data: T? = null): ComponentState<T>(data)
    class Error<T>(errorCode: Int, errorMsg: String = ""): ComponentState<T>(null, errorCode, errorMsg)
    class Empty<T> : ComponentState<T>()
}
