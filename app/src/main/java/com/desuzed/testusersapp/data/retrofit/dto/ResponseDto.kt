package com.desuzed.testusersapp.data.retrofit.dto

import com.desuzed.testusersapp.data.EntityMapper
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.data.model.Response
import com.google.gson.annotations.SerializedName

class ResponseDto(
    @SerializedName("page") var page: Int = 0,
    @SerializedName("per_page") var perPage: Int = 0,
    @SerializedName("total") var total: Int = 0,
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("data") var users: List<UserRetrofitDto> = arrayListOf(),
    @SerializedName("support") var support: SupportDto = SupportDto()
)

class ResponseMapper : EntityMapper<ResponseDto, Response> {
    override fun mapFromEntity(entity: ResponseDto): Response {
        val listOfUsers = arrayListOf<User>()
        entity.users.forEach {
            listOfUsers.add(UserRetrofitDtoToUserMapper().mapFromEntity(it))
        }
        return Response(
            entity.page, entity.perPage,
            entity.total,
            entity.totalPages,
            listOfUsers,
            SupportMapper().mapFromEntity(entity.support)
        )
    }

}