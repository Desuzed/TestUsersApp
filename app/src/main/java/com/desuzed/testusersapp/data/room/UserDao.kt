package com.desuzed.testusersapp.data.room

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllCachedUsers(): List<UserDTO>

    @Delete
    suspend fun deleteUser(user: UserDTO)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserDTO>)
}