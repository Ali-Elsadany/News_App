package com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.domain.model.ApiResult

interface NewsLocalDataSource {

    suspend fun getSources(categoryId: String): ApiResult<List<SourceDM>>

    suspend fun saveSources(sources: List<SourceDM>)

    suspend fun getArticles(sourceId: String): ApiResult<ArticlesResponse>
}