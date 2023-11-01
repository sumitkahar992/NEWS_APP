package com.example.news_app.data.di

import com.example.news_app.data.remote.api.ApiService
import com.example.news_app.data.repository.UserRepositoryImpl
import com.example.news_app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideUserApi(): ApiService = ApiService()

    @Provides
    fun provideUserRepository(api: ApiService): UserRepository = UserRepositoryImpl(api)
}
