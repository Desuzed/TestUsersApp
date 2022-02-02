package com.desuzed.testusersapp

import android.app.Application
import com.desuzed.testusersapp.data.ErrorProviderImpl
import com.desuzed.testusersapp.data.repo.LocalDataSourceImpl
import com.desuzed.testusersapp.data.repo.RemoteDataSourceImpl
import com.desuzed.testusersapp.data.repo.RepoApp
import com.desuzed.testusersapp.data.repo.RepoAppImpl
import com.desuzed.testusersapp.data.room.RoomDbApp

class App : Application() {
    private val roomDbApp by lazy { RoomDbApp.getDatabase(this) }
    private val repositoryApp by lazy {
        RepoAppImpl(
            LocalDataSourceImpl(roomDbApp.userDao(), ErrorProviderImpl(resources)),
            RemoteDataSourceImpl()
        )
    }

    fun getRepo(): RepoApp = repositoryApp
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}