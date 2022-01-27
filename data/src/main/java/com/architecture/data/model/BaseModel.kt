package com.architecture.data.model

import com.squareup.moshi.JsonClass

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   :
 */
@JsonClass(generateAdapter = true)
open class BaseModel {
    var errorCode: Int = 0
    var errorMsg: String = ""
}
