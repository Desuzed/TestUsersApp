package com.desuzed.testusersapp.data.retrofit.dto

import com.desuzed.testusersapp.data.EntityMapper
import com.desuzed.testusersapp.data.model.Error
import com.google.gson.annotations.SerializedName

class ErrorRetrofitDto {
    @SerializedName("status")
    var status: String = ""

    @SerializedName("code")
    var code: String = ""

    @SerializedName("message")
    var message: String = ""
}

class ErrorMapper : EntityMapper<ErrorRetrofitDto, Error> {
    override fun mapFromEntity(entity: ErrorRetrofitDto): Error =
        Error(entity.status, entity.code, entity.message)
}
