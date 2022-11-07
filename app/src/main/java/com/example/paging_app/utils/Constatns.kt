package com.example.paging_app.utils

import com.example.paging_app.domain.model.news.NewsResponse


const val STARTING_INDEX = 1
const val NETWORK_PAGE_SIZE = 10



val dummyNewsItem = NewsResponse.Article(
    author = "Some Author",
    content = "This is random content This is random content This is random content This is random content This is random content This is random content This is random content This is random content This is random content ",
    description = "This is description This is description This is description This is description This is description",
    publishedAt = "SDJKSDHSJDHSJD",
    source = null,
    title = "NEWS TITLE GOES HERE NEWS TITLE GOES",
    url = "dsdsdsds",
    urlToImage = null

)