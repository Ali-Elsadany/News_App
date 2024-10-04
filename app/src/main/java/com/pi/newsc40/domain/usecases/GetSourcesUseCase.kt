package com.pi.newsc40.domain.usecases

import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.domain.model.ApiResult
import com.pi.newsc40.domain.model.Source
import com.pi.newsc40.domain.repositories.NewsRepo
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun execute(categoryId: String): ApiResult<List<Source>> {
        return newsRepo.getSources(categoryId)
    }
}