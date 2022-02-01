package com.desuzed.testusersapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.desuzed.testusersapp.data.repo.RepoApp
import com.desuzed.testusersapp.ui.detail.DetailInfoViewModel
import com.desuzed.testusersapp.ui.users.UsersViewModel

class ViewModelFactory(private val repositoryApp: RepoApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UsersViewModel::class.java) -> {
                UsersViewModel(repositoryApp) as T
            }
            modelClass.isAssignableFrom(DetailInfoViewModel::class.java) -> {
                DetailInfoViewModel(repositoryApp) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}