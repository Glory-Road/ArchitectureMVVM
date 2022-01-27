package com.architecture.data.model

import com.squareup.moshi.JsonClass

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   : 网络请求返回Model基类封装
 */
@JsonClass(generateAdapter = true)
class HttpResultModel<T>(val data: T): BaseModel()