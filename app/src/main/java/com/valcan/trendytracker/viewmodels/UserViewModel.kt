package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.UserEntity
import com.valcan.trendytracker.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.SharedPreferences
import androidx.core.content.edit

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    companion object {
        private const val KEY_SELECTED_USER_ID = "selected_user_id"
    }

    val users = userRepository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedUser = MutableStateFlow<UserEntity?>(null)
    val selectedUser = _selectedUser.asStateFlow()

    init {
        viewModelScope.launch {
            val savedUserId = sharedPreferences.getLong(KEY_SELECTED_USER_ID, -1)
            if (savedUserId != -1L) {
                userRepository.getUserById(savedUserId)?.let { user ->
                    _selectedUser.emit(user)
                }
            }
        }
    }

    fun selectUser(user: UserEntity) {
        viewModelScope.launch {
            _selectedUser.emit(user)
            sharedPreferences.edit {
                putLong(KEY_SELECTED_USER_ID, user.id)
            }
        }
    }
} 