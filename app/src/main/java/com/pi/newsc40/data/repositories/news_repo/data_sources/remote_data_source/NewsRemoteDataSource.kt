package com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.SourcesResponse

interface NewsRemoteDataSource{
    suspend fun getSources(categoryId: String): SourcesResponse

    suspend fun getArticles(sourceId: String): ArticlesResponse
}