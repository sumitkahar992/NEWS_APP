package com.example.paging_app.presentation.newsui.news

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.paging_app.domain.model.news.NewsResponse
import com.example.paging_app.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


    var allNews : Flow<PagingData<NewsResponse.Article>> = emptyFlow()

    fun getNewsByCategory(category: String) {
        allNews = newsRepository.getNewsByCategory(category)
    }

}