package com.desuzed.testusersapp.data.repo

import com.desuzed.everyweather.data.network.retrofit.NetworkResponse
import com.desuzed.testusersapp.data.retrofit.UserService
import com.desuzed.testusersapp.data.retrofit.dto.ErrorRetrofitDto
import com.desuzed.testusersapp.data.retrofit.dto.Response

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun fetchUsers(): NetworkResponse<Response, ErrorRetrofitDto>  =
        UserService
            .getInstance()
            .fetchResponse("2")

}

interface RemoteDataSource {
    suspend fun fetchUsers(): NetworkResponse<Response, ErrorRetrofitDto>
}