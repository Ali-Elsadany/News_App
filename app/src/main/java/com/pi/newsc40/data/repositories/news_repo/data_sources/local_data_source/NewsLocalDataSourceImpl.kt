package com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.Source
import com.pi.newsc40.data.database.MyDataBase

class NewsLocalDataSourceImpl(private var myDataBase: MyDataBase) : NewsLocalDataSource {

    override suspend fun getSources(categoryId: String): List<Source> {
        return myDataBase.getSourcesDao().getSources(categoryId)
    }

    override suspend fun saveSources(sources: List<Source>) {
        myDataBase.getSourcesDao().insertSources(sources)
    }

    override suspend fun getArticles(sourceId: String): ArticlesResponse {
        return ArticlesResponse(articles = emptyList())
    }
}