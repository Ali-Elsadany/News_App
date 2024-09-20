package com.pi.newsc40.data.repositories.news_repo

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.Source
import com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source.NewsRemoteDataSource
import com.pi.newsc40.data.utils.InternetConnectionChecker

class NewsRepoImpl(private var localDataSource: NewsLocalDataSource,
                   private var remoteDataSource:NewsRemoteDataSource,

    ): NewsRepo {

    override suspend fun getSources(categoryId: String): List<Source>{
        return if(InternetConnectionChecker.isOnline()){
            val sourcesResponse =  remoteDataSource.getSources(categoryId)
            localDataSource.saveSources(sourcesResponse.sources!!)
            sourcesResponse.sources
        }else {
            localDataSource.getSources(categoryId)
        }

    }

    override suspend fun getArticles(sourceId: String): ArticlesResponse{
        // todo: check internet connection here
        try{
            return if(InternetConnectionChecker.isOnline()){
                val sourcesResponse =  remoteDataSource.getArticles(sourceId)
                sourcesResponse;
            }else {
                localDataSource.getArticles(sourceId)
            }
        }catch (e: Throwable){
            throw e;
        }
    }
}