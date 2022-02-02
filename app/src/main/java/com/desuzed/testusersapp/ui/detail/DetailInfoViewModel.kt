package com.desuzed.testusersapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.data.repo.RepoApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailInfoViewModel(private val repo: RepoApp) : ViewModel(), IDetailInfoViewModel {
    private val _currentUserStateFlow = MutableStateFlow(User.emptyUser())
    val currentUserStateFlow: StateFlow<User> = _currentUserStateFlow

    override fun updateCurrentUserStateFlow(id: Int) {
        viewModelScope.launch {
            repo.getUserById(id).collect {
                _currentUserStateFlow.value = it
            }
        }
    }

    override fun updateUser(user: User) {
        repo.updateUser(user)
    }

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }
}

interface IDetailInfoViewModel {
    fun updateCurrentUserStateFlow(id: Int)
    fun updateUser(user: User)
}