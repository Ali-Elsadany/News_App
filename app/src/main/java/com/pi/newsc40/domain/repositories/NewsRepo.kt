package com.pi.newsc40.domain.repositories

import com.pi.newsc40.domain.model.ApiResult
import com.pi.newsc40.domain.model.Article
import com.pi.newsc40.domain.model.Source

interface NewsRepo {

    suspend fun getSources(categoryId: String): ApiResult<List<Source>>

    suspend fun getArticles(sourceId: String): ApiResult<List<Article>>
}