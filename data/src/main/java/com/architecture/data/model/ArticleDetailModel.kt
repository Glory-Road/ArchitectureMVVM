package com.architecture.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDetailModel(
    val apkLink: String,
    val audit: Int,
    val author: String,
)
