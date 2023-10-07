package com.example.paging_app.data.remote.api

import com.example.paging_app.domain.model.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "2671fd27a2834fc1a0671903c9213771"
    ): NewsResponse

    @GET("everything?q=technology")
    suspend fun getEverything(
        @Query("apiKey") apiKey: String = "2671fd27a2834fc1a0671903c9213771"
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "2671fd27a2834fc1a0671903c9213771",
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = "2671fd27a2834fc1a0671903c9213771",
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse
}
