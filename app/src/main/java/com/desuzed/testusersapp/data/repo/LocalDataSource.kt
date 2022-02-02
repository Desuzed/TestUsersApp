package com.desuzed.testusersapp.data.repo

import com.desuzed.testusersapp.data.ErrorProvider
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.data.room.UserDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val userDao: UserDao, private val errorProvider: ErrorProvider    ) : LocalDataSource {
    private var job: Job? = null // todo clear job
    override fun insertUsers(list: List<User>) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.insertUsers(list)
        }
    }

    override fun deleteAllUsers() {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
        }
    }

    override fun deleteUser(userDto: User) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteUser(userDto)
        }
    }

    //todo проверить
    override suspend fun getCachedUsers(): Flow<List<User>> = withContext(Dispatchers.IO) {
        userDao.getAllCachedUsers()
    }

    override suspend fun getUserById(id: Int): Flow<User> = withContext(Dispatchers.IO) {
        userDao.getUserById(id)
    }

    override fun updateUser(user: User) {
        job = CoroutineScope(Dispatchers.IO).launch {
            userDao.updateUser(user)
        }
    }

    override fun parseCode(code: Int): String {
        return errorProvider.parseCode(code)
    }
}

interface LocalDataSource : ErrorProvider {
    fun insertUsers(list: List<User>)
    fun deleteAllUsers()
    fun deleteUser(userDto: User)
    suspend fun getCachedUsers(): Flow<List<User>>
    suspend fun getUserById(id: Int): Flow<User>
    fun updateUser(user: User)
}