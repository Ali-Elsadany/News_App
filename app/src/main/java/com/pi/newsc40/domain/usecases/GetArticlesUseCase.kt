package com.pi.newsc40.domain.usecases

import com.pi.newsc40.data.api.model.ArticleDM
import com.pi.newsc40.domain.model.ApiResult
import com.pi.newsc40.domain.model.Article
import com.pi.newsc40.domain.repositories.NewsRepo
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun execute(tabId: String): ApiResult<List<Article>> {
        return newsRepo.getArticles(tabId)
    }
}