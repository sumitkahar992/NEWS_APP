package com.example.news_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news_app.common.Resource
import com.example.news_app.data.paging.NewsPagingSource
import com.example.news_app.data.paging.SearchNewsPagingSource
import com.example.news_app.data.remote.api.NewsApi
import com.example.news_app.domain.model.news.NewsResponse
import com.example.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override fun getTopHeadlines(): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading())

            val result = newsApi.getTopHeadlines()
            emit(Resource.Success(result))
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = "Couldn't load Headlines ?."
                )
            )
        }
    }

    override fun getEverything(): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading())

            val result = newsApi.getEverything()
            emit(Resource.Success(result))
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = "Could't load News ?.IO"
                )
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = "Could't load News ?.Http"
                )
            )
        }
    }

    override fun getNewsByCategory(
        category: String
    ): Flow<PagingData<NewsResponse.Article>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            NewsPagingSource(newsApi, category)
        }
    ).flow

    override fun searchNews(query: String): Flow<PagingData<NewsResponse.Article>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchNewsPagingSource(newsApi, query)
        }
    ).flow

    override fun searchNewsFlow(query: String): Flow<PagingData<NewsResponse.Article>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchNewsPagingSource(newsApi, query)
        }
    ).flow

    override fun getNewsCategories(): List<String> = listOf(
        "Sports",
        "Health",
        "Entertainment",
        "Science",
        "Business",
        "Technology"
    )
}
