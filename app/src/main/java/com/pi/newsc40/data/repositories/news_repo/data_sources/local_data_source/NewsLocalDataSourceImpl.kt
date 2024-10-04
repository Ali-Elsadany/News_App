package com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.data.database.MyDataBase
import com.pi.newsc40.domain.model.ApiResult
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(private var myDataBase: MyDataBase) :
    NewsLocalDataSource {

    override suspend fun getSources(categoryId: String): ApiResult<List<SourceDM>> {
        return try {
            ApiResult.Success(myDataBase.getSourcesDao().getSources(categoryId))
        } catch (e: Throwable) {
            ApiResult.Error(e.localizedMessage ?: "")
        }
    }

    override suspend fun saveSources(sources: List<SourceDM>) {
        myDataBase.getSourcesDao().insertSources(sources)
    }

    override suspend fun getArticles(sourceId: String): ApiResult<ArticlesResponse> {
        return ApiResult.Success(ArticlesResponse(articles = emptyList()))
    }
}