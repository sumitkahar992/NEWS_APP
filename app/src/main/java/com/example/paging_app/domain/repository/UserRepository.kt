package com.example.paging_app.domain.repository

import com.example.paging_app.domain.model.paging.UserResponse

interface UserRepository {

    suspend fun getUsers(page: Int, limit: Int): UserResponse
}
