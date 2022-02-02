package com.desuzed.testusersapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.data.repo.RepoApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailInfoViewModel(private val repo: RepoApp) : ViewModel() {
    //todo refactor
    private val _currentUserStateFlow = MutableStateFlow(User(0, "", "", "", ""))
    val currentUserStateFlow: StateFlow<User> = _currentUserStateFlow

    fun updateCurrentUserStateFlow(id: Int) {
        viewModelScope.launch {
            repo.getUserById(id).collect {
                _currentUserStateFlow.value = it
            }
        }
    }

    fun updateUser(user: User) {
        repo.updateUser(user)
        Log.i("TAG", "updateUser: ${currentUserStateFlow.value} \n ${_currentUserStateFlow.value}")
    }


}