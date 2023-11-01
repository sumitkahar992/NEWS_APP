package com.example.news_app.data.repository

import com.example.news_app.data.remote.api.ApiService
import com.example.news_app.domain.model.paging.UserResponse
import com.example.news_app.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: ApiService
) : UserRepository {

//    var error = 0

    override suspend fun getUsers(page: Int, limit: Int): UserResponse {
//        delay(3000)
//        error++
//        if (error == 4)
//            throw IOException("some Error Occurred")
        return api.getUser(page, limit)
    }
}
