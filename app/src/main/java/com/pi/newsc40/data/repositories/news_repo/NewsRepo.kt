package com.pi.newsc40.data.repositories.news_repo

import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.Source

interface NewsRepo {

    suspend fun getSources(categoryId: String): List<Source>

    suspend fun getArticles(sourceId: String): ArticlesResponse
}