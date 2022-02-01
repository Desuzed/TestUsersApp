package com.desuzed.testusersapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.data.repo.RepoApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailInfoViewModel(private val repo: RepoApp) : ViewModel() {
    //todo refactor
    private val _currentUsersStateFlow = MutableStateFlow(User(0, "", "", "", ""))
    val currentUsersStateFlow: StateFlow<User> = _currentUsersStateFlow

    fun updateCurrentUserStateFlow(id: Int) {
        viewModelScope.launch {
            _currentUsersStateFlow.value = repo.getUserById(id)
        }
    }


}