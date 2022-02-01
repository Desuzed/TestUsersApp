package com.desuzed.testusersapp.data.model

import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.data.retrofit.dto.UserRetrofitDto

class Response(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val users: List<User>,
    val support: Support
)
