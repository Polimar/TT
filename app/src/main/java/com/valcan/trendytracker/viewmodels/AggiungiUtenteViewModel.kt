package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.UserEntity
import com.valcan.trendytracker.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AggiungiUtenteViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _saveResult = MutableSharedFlow<Boolean>()
    val saveResult = _saveResult.asSharedFlow()

    fun saveUser(
        name: String,
        isMale: Boolean
    ) = viewModelScope.launch {
        try {
            val user = UserEntity(
                name = name,
                isMale = isMale
            )
            userRepository.insert(user)
            _saveResult.emit(true)
        } catch (e: Exception) {
            _saveResult.emit(false)
        }
    }
} 