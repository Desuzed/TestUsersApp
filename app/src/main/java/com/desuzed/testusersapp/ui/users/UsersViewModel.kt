package com.desuzed.testusersapp.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.data.repo.RepoApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val repo: RepoApp) : ViewModel(), IUsersViewModel {
    private val _usersStateFlow = MutableStateFlow(emptyList<User>())
    val usersStateFlow: StateFlow<List<User>> = _usersStateFlow

    private val _errorStateFlow = MutableStateFlow("")
    val errorStateFlow: StateFlow<String> = _errorStateFlow

    init {
        viewModelScope.launch {
            repo.getCachedUsers().collect {
                _usersStateFlow.value = it
            }
        }
    }

    override fun fetchUsers() {
        viewModelScope.launch {
            val error = repo.getUsersFromApi()
            if (error != null) {
                _errorStateFlow.value = error.message
            }
        }
    }

    override fun deleteUser(user: User) {
        repo.deleteUser(user)
    }

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }
}

interface IUsersViewModel {
    fun fetchUsers()
    fun deleteUser(user: User)
}
