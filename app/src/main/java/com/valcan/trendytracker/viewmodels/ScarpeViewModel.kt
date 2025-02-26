package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.ShoeEntity
import com.valcan.trendytracker.data.repository.ShoeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScarpeViewModel @Inject constructor(
    private val shoeRepository: ShoeRepository
) : ViewModel() {

    private val _currentUserId = MutableStateFlow<Long?>(null)
    
    val shoes = _currentUserId
        .filterNotNull()
        .flatMapLatest { userId ->
            shoeRepository.getShoesForUser(userId)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setCurrentUser(userId: Long) {
        _currentUserId.value = userId
    }

    fun addShoe(shoe: ShoeEntity) = viewModelScope.launch {
        shoeRepository.insert(shoe)
    }

    fun deleteShoe(shoe: ShoeEntity) = viewModelScope.launch {
        shoeRepository.delete(shoe)
    }
} 