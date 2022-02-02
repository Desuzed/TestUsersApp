package com.desuzed.testusersapp.data.model

class Response(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val users: List<User>,
    val support: Support
)
