package com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.Source

interface NewsLocalDataSource {

    suspend fun getSources(categoryId: String): List<Source>

    suspend fun saveSources(sources: List<Source>)

    suspend fun getArticles(sourceId: String): ArticlesResponse
}