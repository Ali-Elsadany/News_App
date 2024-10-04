package com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source

import com.pi.newsc40.data.api.ApiManager
import com.pi.newsc40.data.api.WebServices
import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.SourcesResponse
import com.pi.newsc40.domain.model.ApiResult
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private var webServices: WebServices) :
    NewsRemoteDataSource {

    override suspend fun getSources(categoryId: String): ApiResult<SourcesResponse> {
        return try {
             ApiResult.Success(data = webServices.getSources(category = categoryId))
        } catch (e: Exception) {
             ApiResult.Error(e.localizedMessage ?: "")
        }
    }

    override suspend fun getArticles(sourceId: String): ApiResult<ArticlesResponse> {
        return try {
            ApiResult.Success(data = webServices.getArticles(tabId = sourceId))
        } catch (e: Exception) {
            ApiResult.Error(e.localizedMessage ?: "")
        }
    }
}