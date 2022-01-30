package com.desuzed.testusersapp.data.repo

import com.desuzed.testusersapp.data.room.UserDTO
import com.desuzed.testusersapp.data.room.UserDao
import kotlinx.coroutines.*

class LocalDataSourceImpl(private val userDao: UserDao) : LocalDataSource {
    private var job: Job? = null
    override fun insertUsers(list: List<UserDTO>) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.insertUsers(list)
        }
    }

    override fun deleteAllUsers() {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
        }
    }

    override fun deleteUser(userDTO: UserDTO) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteUser(userDTO)
        }
    }

    override suspend fun getCachedUsers(): List<UserDTO> = withContext(Dispatchers.IO) {
        userDao.getAllCachedUsers()
    }
}

interface LocalDataSource {
    fun insertUsers(list: List<UserDTO>)
    fun deleteAllUsers()
    fun deleteUser(userDTO: UserDTO)
    suspend fun getCachedUsers(): List<UserDTO>

}