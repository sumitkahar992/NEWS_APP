package com.example.paging_app.domain.model.paging

import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "data")
    val users: List<User>,
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "total")
    val total: Int
)
