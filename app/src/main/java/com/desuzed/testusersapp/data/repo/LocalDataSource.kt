package com.desuzed.testusersapp.data.repo

import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.data.room.UserDao
import com.desuzed.testusersapp.data.room.UserDto
import com.desuzed.testusersapp.data.room.UserDtoToUserMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class LocalDataSourceImpl(private val userDao: UserDao) : LocalDataSource {
    private var job: Job? = null
    override fun insertUsers(list: List<UserDto>) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.insertUsers(list)
        }
    }

    override fun deleteAllUsers() {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
        }
    }

    override fun deleteUser(userDto: UserDto) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteUser(userDto)
        }
    }

    //todo проверить
    override suspend fun getCachedUsers(): Flow<List<User>> = withContext(Dispatchers.IO) {
        val usersDto = userDao.getAllCachedUsers()
        val resultMap = usersDto.transform<List<UserDto>, List<User>> { list ->
            val listOfUsers = arrayListOf<User>()
            list.forEach { userDto ->
                listOfUsers.add(UserDtoToUserMapper().mapFromEntity(userDto))
            }
            emit(listOfUsers)
        }
        resultMap
    }

    override suspend fun getUserById(id: Int): User = withContext(Dispatchers.IO) {
        UserDtoToUserMapper().mapFromEntity(userDao.getUserById(id))
    }
}

interface LocalDataSource {
    fun insertUsers(list: List<UserDto>)
    fun deleteAllUsers()
    fun deleteUser(userDto: UserDto)
    suspend fun getCachedUsers(): Flow<List<User>>
    suspend fun getUserById(id: Int): User
}