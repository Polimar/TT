package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.WardrobeEntity
import com.valcan.trendytracker.data.repository.WardrobeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArmadioViewModel @Inject constructor(
    private val wardrobeRepository: WardrobeRepository
) : ViewModel() {

    private val _currentUserId = MutableStateFlow<Long?>(null)

    val wardrobes = _currentUserId
        .filterNotNull()
        .flatMapLatest { userId ->
            wardrobeRepository.getWardrobesWithUserItems(userId)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setCurrentUser(userId: Long) {
        _currentUserId.value = userId
    }

    fun addWardrobe(wardrobe: WardrobeEntity) = viewModelScope.launch {
        wardrobeRepository.insert(wardrobe)
    }

    fun deleteWardrobe(wardrobe: WardrobeEntity) = viewModelScope.launch {
        wardrobeRepository.delete(wardrobe)
    }

    fun getWardrobeItemCounts(wardrobeId: Long, userId: Long) = 
        wardrobeRepository.getUserItemCountsInWardrobe(wardrobeId, userId)
} 