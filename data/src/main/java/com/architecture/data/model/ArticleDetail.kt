package com.architecture.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDetail(
    val apkLink: String,
    val audit: Int,
    val author: String,
)
