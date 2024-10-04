package com.pi.newsc40.data.repositories.news_repo

import com.pi.newsc40.data.api.model.ArticleDM
import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source.NewsRemoteDataSource
import com.pi.newsc40.data.utils.InternetConnectionChecker
import com.pi.newsc40.domain.mappers.ArticleMapper
import com.pi.newsc40.domain.mappers.SourceMapper
import com.pi.newsc40.domain.model.ApiResult
import com.pi.newsc40.domain.model.Article
import com.pi.newsc40.domain.model.Source
import com.pi.newsc40.domain.repositories.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private var localDataSource: NewsLocalDataSource,
    private var remoteDataSource: NewsRemoteDataSource,
    private var souresMapper: SourceMapper,
    private var articleMapper: ArticleMapper
) : NewsRepo {

    override suspend fun getSources(categoryId: String): ApiResult<List<Source>> {
        return if (InternetConnectionChecker.isOnline()) {
            val result = remoteDataSource.getSources(categoryId)
            when (result) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> {
                    localDataSource.saveSources(result.data.sources!!)
                    ApiResult.Success(souresMapper.mapSourcesDMToSources(result.data.sources))
                }
            }
        } else {
            val result = localDataSource.getSources(categoryId)
            when (result) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> ApiResult.Success(souresMapper.mapSourcesDMToSources(result.data))
            }
        }

    }

    override suspend fun getArticles(sourceId: String): ApiResult<List<Article>> {
        return if (InternetConnectionChecker.isOnline()) {
            val result = remoteDataSource.getArticles(sourceId)
            when (result) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> {
                    ApiResult.Success(articleMapper.mapArticlesDMToArticles(result.data.articles!!))
                }
            }
        } else {
            val result = localDataSource.getArticles("")
            when (result) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> ApiResult.Success(
                    articleMapper.mapArticlesDMToArticles(
                        result.data.articles!!
                    )
                )
            }
        }
    }
}