package com.desuzed.testusersapp.data.room

import androidx.room.*
import com.desuzed.testusersapp.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllCachedUsers(): Flow<List<User>>

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table WHERE id=:id")
    fun getUserById(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<User>)

    @Update
    fun updateUser(user: User)
}