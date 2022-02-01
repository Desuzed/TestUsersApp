package com.desuzed.testusersapp.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllCachedUsers(): Flow<List<UserDto>>

    @Delete
    suspend fun deleteUser(user: UserDto)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table WHERE id=:id")
    suspend fun getUserById(id: Int): UserDto

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserDto>)
}