package com.architecture.data.mapper

import com.architecture.data.model.ArticleDetailModel
import com.architecture.data.model.BaseModel
import com.architecture.data.model.HttpResultModel
import com.architecture.domain.model.ArticleDetail
import com.architecture.domain.model.HttpResult
import com.architecture.domain.model.Main

fun ArticleDetailModel.toDomain() = ArticleDetail(apkLink, audit, author)