package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.ClothingEntity
import com.valcan.trendytracker.data.repository.ClothingRepository
import com.valcan.trendytracker.data.repository.WardrobeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AggiungiVestitoViewModel @Inject constructor(
    private val clothingRepository: ClothingRepository,
    private val wardrobeRepository: WardrobeRepository
) : ViewModel() {

    val wardrobes = wardrobeRepository.getAllWardrobes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _saveResult = MutableSharedFlow<Boolean>()
    val saveResult = _saveResult.asSharedFlow()

    fun saveClothing(
        userId: Long,
        name: String,
        type: String,
        color: String,
        season: String,
        wardrobeId: Long?,
        imageUrl: String?
    ) = viewModelScope.launch {
        try {
            val clothing = ClothingEntity(
                userId = userId,
                name = name,
                type = type,
                color = color,
                season = season,
                wardrobeId = wardrobeId,
                imageUrl = imageUrl
            )
            clothingRepository.insert(clothing)
            _saveResult.emit(true)
        } catch (e: Exception) {
            _saveResult.emit(false)
        }
    }
} 