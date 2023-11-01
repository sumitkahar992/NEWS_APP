package com.example.news_app.domain.repository

import com.example.news_app.domain.model.paging.UserResponse

interface UserRepository {

    suspend fun getUsers(page: Int, limit: Int): UserResponse
}
