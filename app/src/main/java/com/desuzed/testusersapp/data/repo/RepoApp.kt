package com.desuzed.testusersapp.data.repo

import com.desuzed.everyweather.data.network.retrofit.NetworkResponse
import com.desuzed.testusersapp.data.retrofit.dto.ErrorRetrofitDto
import com.desuzed.testusersapp.data.retrofit.dto.UserRetrofitDto
import com.desuzed.testusersapp.data.room.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoAppImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RepoApp {
    override suspend fun getUsersFromApi(): Pair<List<UserRetrofitDto>?, ErrorRetrofitDto?> =
        withContext(Dispatchers.IO) {
            when (val response = remoteDataSource.fetchUsers()){
                is NetworkResponse.Success -> {
                    Pair(response.body.users, null)
                }
                //TODO Доделать обработку ошибок
                is NetworkResponse.ApiError -> Pair(null, null)
                is NetworkResponse.NetworkError -> Pair(null, null)
                is NetworkResponse.UnknownError -> Pair(null, null)
            }
        }

    override fun insertUsers(list: List<UserDTO>) {
        localDataSource.insertUsers(list)
    }

    override fun deleteAllUsers() {
        localDataSource.deleteAllUsers()
    }

    override fun deleteUser(userDTO: UserDTO) {
        localDataSource.deleteUser(userDTO)
    }

    override suspend fun getCachedUsers(): List<UserDTO> {
        val list = ArrayList<UserDTO>()
        list.add(UserDTO(1, "FirstName", "Sec name", "email", "uri"))
        return list
    }

}

interface RepoApp : LocalDataSource {
    suspend fun getUsersFromApi(): Pair<List<UserRetrofitDto>?, ErrorRetrofitDto?>
}