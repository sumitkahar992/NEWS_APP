package com.example.paging_app.presentation.newsui.news

import androidx.paging.PagingData
import com.example.paging_app.domain.model.news.NewsResponse

data class NewsByCategoryState(
    var isLoading: Boolean = false,
    val news: PagingData<NewsResponse.Article>,
    val error: String? = ""
)
