package com.desuzed.testusersapp.data.repo

import com.desuzed.everyweather.data.network.retrofit.NetworkResponse
import com.desuzed.testusersapp.data.retrofit.UserService
import com.desuzed.testusersapp.data.retrofit.dto.ErrorRetrofitDto
import com.desuzed.testusersapp.data.retrofit.dto.ResponseDto

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun fetchUsers(): NetworkResponse<ResponseDto, ErrorRetrofitDto>  =
        UserService
            .getInstance()
            .fetchResponse("    ")

}

interface RemoteDataSource {
    suspend fun fetchUsers(): NetworkResponse<ResponseDto, ErrorRetrofitDto>
}