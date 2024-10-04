package com.pi.newsc40.data.di

import com.pi.newsc40.domain.repositories.NewsRepo
import com.pi.newsc40.data.repositories.news_repo.NewsRepoImpl
import com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source.NewsRemoteDataSource
import com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindNewsRepo(newsRepoImpl: NewsRepoImpl): NewsRepo

    @Binds
    abstract fun bindNewsLocalDataSource(local: NewsLocalDataSourceImpl): NewsLocalDataSource

    @Binds
    abstract fun bindNewsRemoteDataSource(local: NewsRemoteDataSourceImpl): NewsRemoteDataSource

//    @Provides
//    fun provideNewsRepo(localDataSource: NewsLocalDataSource,
//                        remoteDataSource: NewsRemoteDataSource): NewsRepo{
//        return NewsRepoImpl(localDataSource, remoteDataSource)
//    }
//
}