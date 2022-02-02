package com.desuzed.testusersapp.data.repo

import android.util.Log
import com.desuzed.everyweather.data.network.retrofit.NetworkResponse
import com.desuzed.testusersapp.data.model.Error
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.data.retrofit.dto.UserRetrofitDtoToUserMapper
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
                    val roomUsers = arrayListOf<User>()
                    response.body.users.forEach {
                        roomUsers.add(UserRetrofitDtoToUserMapper().mapFromEntity(it))
                    }
                    deleteAllUsers()
                    insertUsers(roomUsers)
                    null
                }
                is NetworkResponse.ApiError -> {
                    Log.i("network", "getUsersFromApi: ApiError: ${response.body}")
                    Error("Api error", response.code.toString(), parseCode(response.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.i("network", "getUsersFromApi: NetworkError ${response.error.message}")
                    Error("Network error", response.error.message.toString(), parseCode(10))
                }
                is NetworkResponse.UnknownError -> {
                    Log.i("network", "getUsersFromApi: UnknownError ${response.error?.message}")
                    Error("Unknown error", response.error?.message.toString(), parseCode(0))
                }
            }
        }


    override fun insertUsers(list: List<User>) {
        localDataSource.insertUsers(list)
    }

    override fun deleteAllUsers() {
        localDataSource.deleteAllUsers()
    }

    override fun deleteUser(userDto: User) {
        localDataSource.deleteUser(userDto)
    }

    override suspend fun getCachedUsers(): Flow<List<User>> {
        return localDataSource.getCachedUsers()
    }

    override suspend fun getUserById(id: Int): Flow<User> {
        return localDataSource.getUserById(id)
    }

    override fun updateUser(user: User) {
        localDataSource.updateUser(user)
    }

    override fun parseCode(code: Int): String {
        return localDataSource.parseCode(code)
    }

}

interface RepoApp : LocalDataSource {
    suspend fun getUsersFromApi(): Error?
}