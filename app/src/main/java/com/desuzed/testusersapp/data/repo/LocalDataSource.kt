package com.desuzed.testusersapp.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.data.room.UserDTO
import com.desuzed.testusersapp.data.room.UserDao
import com.desuzed.testusersapp.data.room.UserDtoToUserMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

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
//todo проверить
    override suspend fun getCachedUsers(): Flow<List<User>> = withContext(Dispatchers.IO) {
        val usersDto = userDao.getAllCachedUsers()
        val resultMap = usersDto.transform<List<UserDTO>, List<User>> { list ->
            val listOfUsers = arrayListOf<User>()
            list.forEach { userDto ->
                listOfUsers.add(UserDtoToUserMapper().mapFromEntity(userDto))
            }
            emit(listOfUsers)
        }
        resultMap
    }
}

interface LocalDataSource {
    fun insertUsers(list: List<UserDTO>)
    fun deleteAllUsers()
    fun deleteUser(userDTO: UserDTO)
    suspend fun getCachedUsers(): Flow<List<User>>
}