package com.desuzed.testusersapp.data.repo

import com.desuzed.everyweather.data.network.retrofit.NetworkResponse
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.data.model.Error
import com.desuzed.testusersapp.data.retrofit.dto.UserRetrofitToRoomMapper
import com.desuzed.testusersapp.data.room.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepoAppImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RepoApp {
    override suspend fun getUsersFromApi(): Error? =
        withContext(Dispatchers.IO) {
            when (val response = remoteDataSource.fetchUsers()) {
                is NetworkResponse.Success -> {
                    //todo refactoring
                    val roomUsers = arrayListOf<UserDto>()
                    response.body.users.forEach {
                        roomUsers.add(UserRetrofitToRoomMapper().mapFromEntity(it))
                    }
                    deleteAllUsers()
                    insertUsers(roomUsers)
                    null
                }
                //TODO Доделать обработку ошибок
                is NetworkResponse.ApiError ->  null
                is NetworkResponse.NetworkError ->  null
                is NetworkResponse.UnknownError -> null
            }
        }

    override fun insertUsers(list: List<UserDto>) {
        localDataSource.insertUsers(list)
    }

    override fun deleteAllUsers() {
        localDataSource.deleteAllUsers()
    }

    override fun deleteUser(userDto: UserDto) {
        localDataSource.deleteUser(userDto)
    }

    override suspend fun getCachedUsers(): Flow<List<User>> {
        return localDataSource.getCachedUsers()
    }

}

interface RepoApp : LocalDataSource {
    suspend fun getUsersFromApi(): Error?
}