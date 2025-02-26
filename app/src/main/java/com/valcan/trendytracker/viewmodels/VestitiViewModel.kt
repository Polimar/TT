package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.ClothingEntity
import com.valcan.trendytracker.data.repository.ClothingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VestitiViewModel @Inject constructor(
    private val clothingRepository: ClothingRepository
) : ViewModel() {

    private val _currentUserId = MutableStateFlow<Long?>(null)
    
    val clothes = _currentUserId
        .filterNotNull()
        .flatMapLatest { userId ->
            clothingRepository.getClothesForUser(userId)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setCurrentUser(userId: Long) {
        _currentUserId.value = userId
    }

    fun addClothing(clothing: ClothingEntity) = viewModelScope.launch {
        clothingRepository.insert(clothing)
    }

    fun deleteClothing(clothing: ClothingEntity) = viewModelScope.launch {
        clothingRepository.delete(clothing)
    }
} 