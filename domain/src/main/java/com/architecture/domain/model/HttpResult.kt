package com.architecture.domain.model

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   : 网络请求返回Model基类封装
 */
class HttpResult<T>(val data: T): BaseModel()