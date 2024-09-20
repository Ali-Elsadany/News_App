package com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source

import com.pi.newsc40.data.api.ApiManager
import com.pi.newsc40.data.api.WebServices
import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.SourcesResponse

class NewsRemoteDataSourceImpl2(var webServices: WebServices): NewsRemoteDataSource{

    override suspend fun getSources(categoryId: String): SourcesResponse{
        return webServices.getSources(category = categoryId)
    }

    override suspend fun getArticles(sourceId: String): ArticlesResponse{
        return webServices.getArticles(tabId = sourceId)
    }
}