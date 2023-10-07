package com.example.paging_app.presentation.newsui.home

import com.example.paging_app.domain.model.news.NewsResponse

data class HomeDataState(
    var isLoading: Boolean = false,
    val news: HomeData = HomeData(emptyList(), emptyList()),
    val error: String? = ""
)

data class HomeData(
    val banners: List<NewsResponse.Article> = emptyList(),
    val everything: List<NewsResponse.Article> = emptyList()
)
