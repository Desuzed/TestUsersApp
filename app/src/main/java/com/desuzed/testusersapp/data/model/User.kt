package com.desuzed.testusersapp

import android.os.Parcelable
import com.desuzed.testusersapp.data.retrofit.dto.UserRetrofitDto
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String
) : Parcelable

class UserRetrofitDtoToUserMapper : EntityMapper<UserRetrofitDto, User> {
    override fun mapFromEntity(entity: UserRetrofitDto): User {
        return User(
            entity.id,
            entity.firstName,
            entity.lastName,
            entity.email,
            entity.avatar
        )
    }
}