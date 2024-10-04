package com.pi.newsc40.ui.screens.home.fragments.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pi.newsc40.data.api.model.ArticleDM
import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.domain.model.ApiResult
import com.pi.newsc40.domain.model.Article
import com.pi.newsc40.domain.model.Source
import com.pi.newsc40.domain.usecases.GetArticlesUseCase
import com.pi.newsc40.domain.usecases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val getArticlesUseCase: GetArticlesUseCase,
    val getSourcesUseCase: GetSourcesUseCase
) : ViewModel() {
    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val sourcesLiveData: MutableLiveData<List<Source>> = MutableLiveData()
    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()


    fun getSources(categoryId: String) {
        _isLoadingLiveData.value = true //showLoading()
        errorLiveData.value = null //hideError()
        viewModelScope.launch {
            when (val result = getSourcesUseCase.execute(categoryId)) {
                is ApiResult.Error -> {
                    errorLiveData.value = result.errorMessage
                }

                is ApiResult.Success -> {
                    _isLoadingLiveData.value = false //hideLoading()
                    sourcesLiveData.value = result.data //showTabs(sources)
                }
            }
//            try {
//                val sources = getSourcesUseCase.execute(categoryId)
//
//            } catch (e: Throwable) {

//            }
        }
    }

    fun getArticles(tabId: String) {
        try {
            _isLoadingLiveData.value = true //showLoading()
            errorLiveData.value = null //hideError()
            viewModelScope.launch {
                when (val result = getArticlesUseCase.execute(tabId)) {
                    is ApiResult.Error -> {
                        _isLoadingLiveData.value = false
                        errorLiveData.value = result.errorMessage
                    }

                    is ApiResult.Success -> {
                        _isLoadingLiveData.value = false
                        articlesLiveData.value = result.data
                    }
                }

            }
        } catch (e: Throwable) {

        }
    }
}
//DataSource -> Repo -> ViewModel -> View