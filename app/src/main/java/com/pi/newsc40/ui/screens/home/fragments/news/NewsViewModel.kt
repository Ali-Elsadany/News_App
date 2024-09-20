package com.pi.newsc40.ui.screens.home.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pi.newsc40.data.api.ApiManager
import com.pi.newsc40.data.api.model.Article
import com.pi.newsc40.data.api.model.ArticlesResponse
import com.pi.newsc40.data.api.model.Source
import com.pi.newsc40.data.database.MyDataBase
import com.pi.newsc40.data.repositories.news_repo.NewsRepo
import com.pi.newsc40.data.repositories.news_repo.NewsRepoImpl
import com.pi.newsc40.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source.NewsRemoteDataSource
import com.pi.newsc40.data.repositories.news_repo.data_sources.remote_data_source.NewsRemoteDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(var newsRepo: NewsRepo): ViewModel() {
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val sourcesLiveData: MutableLiveData<List<Source?>> = MutableLiveData()
    val articlesLiveData: MutableLiveData<List<Article?>> = MutableLiveData()


    fun getSources(categoryId: String) {
        isLoadingLiveData.value = true //showLoading()
        errorLiveData.value = null //hideError()
        viewModelScope.launch {
            try {
                val sources = newsRepo.getSources(categoryId)
                isLoadingLiveData.value = false //hideLoading()
                sourcesLiveData.value = sources //showTabs(sources)
            } catch (e: Throwable) {
                errorLiveData.value =
                    e.localizedMessage ?: "Something went wrong etc..."
            }
        }
    }

    fun getArticles(tabId: String) {
        try {
            isLoadingLiveData.value = true //showLoading()
            errorLiveData.value = null //hideError()
            viewModelScope.launch {
                val articlesResponse = newsRepo.getArticles(tabId)
                isLoadingLiveData.value = false
                articlesLiveData.value = articlesResponse.articles!!
            }
        } catch (e: Throwable) {
            isLoadingLiveData.value = false
            errorLiveData.value =
                e.localizedMessage ?: "Something went wrong etc..."
        }
    }
}