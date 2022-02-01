package com.desuzed.testusersapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.desuzed.testusersapp.EntityMapper
import com.desuzed.testusersapp.User

@Entity(tableName = "user_table")
class UserDto(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "avatar") val avatar: String
)

class UserDtoToUserMapper : EntityMapper<UserDto, User> {
    override fun mapFromEntity(entity: UserDto): User =
        User(
            entity.id,
            entity.firstName,
            entity.lastName,
            entity.email,
            entity.avatar
        )
}

class UserToUserDtoMapper : EntityMapper<User, UserDto> {
    override fun mapFromEntity(entity: User): UserDto =
        UserDto(
            entity.id,
            entity.firstName,
            entity.lastName,
            entity.email,
            entity.avatar
        )
}