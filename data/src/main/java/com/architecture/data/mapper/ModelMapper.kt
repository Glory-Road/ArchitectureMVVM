package com.architecture.data.mapper

import com.architecture.data.model.MainModel
import com.architecture.domain.model.Main

fun MainModel.toDomain() = Main(id)