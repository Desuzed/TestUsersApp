package com.desuzed.testusersapp.data.repo

import com.desuzed.testusersapp.data.room.UserDTO

class RepoAppImpl (private val localDataSource: LocalDataSource) : RepoApp{
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
        val list = ArrayList <UserDTO> ()
        list.add(UserDTO(1, "FirstName", "Sec name", "email", "uri"))
         return list
    }

}

interface RepoApp : LocalDataSource {

}