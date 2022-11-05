package com.example.paging_app.data.repository

import com.example.paging_app.data.remote.api.ApiService
import com.example.paging_app.domain.model.paging.UserResponse
import com.example.paging_app.domain.repository.UserRepository
import kotlinx.coroutines.delay
import okio.IOException

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