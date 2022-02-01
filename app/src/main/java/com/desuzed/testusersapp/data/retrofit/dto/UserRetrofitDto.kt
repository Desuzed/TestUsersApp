package com.desuzed.testusersapp.data.retrofit.dto

import com.desuzed.testusersapp.EntityMapper
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.data.room.UserDTO
import com.google.gson.annotations.SerializedName

class UserRetrofitDto (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("email")
    var email: String = "",
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("avatar")
    var avatar: String = ""
)

class UserRetrofitToRoomMapper : EntityMapper<UserRetrofitDto, UserDTO> {
    override fun mapFromEntity(entity: UserRetrofitDto): UserDTO {
        return UserDTO(
            entity.id,
            entity.firstName,
            entity.lastName,
            entity.email,
            entity.avatar
        )
    }
}