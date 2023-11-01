package com.example.news_app.data.di.news

import com.example.news_app.data.remote.api.NewsApi
import com.example.news_app.data.repository.NewsRepositoryImpl
import com.example.news_app.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }
}
