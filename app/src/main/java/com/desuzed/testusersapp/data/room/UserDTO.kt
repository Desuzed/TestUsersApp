package com.desuzed.testusersapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.desuzed.testusersapp.EntityMapper
import com.desuzed.testusersapp.User

@Entity(tableName = "user_table")
class UserDTO(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "avatar") val avatar: String
)

class UserDtoToUserMapper : EntityMapper<UserDTO, User> {
    override fun mapFromEntity(entity: UserDTO): User =
        User(
            entity.id,
            entity.firstName,
            entity.lastName,
            entity.email,
            entity.avatar
        )

}
