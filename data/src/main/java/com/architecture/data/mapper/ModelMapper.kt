package com.architecture.data.mapper

import com.architecture.data.model.ArticleDetailModel
import com.architecture.domain.model.ArticleDetail

fun ArticleDetailModel.toDomain() = ArticleDetail(apkLink, audit, author)