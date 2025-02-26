package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.ShoeEntity
import com.valcan.trendytracker.data.repository.ShoeRepository
import com.valcan.trendytracker.data.repository.WardrobeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AggiungiScarpaViewModel @Inject constructor(
    private val shoeRepository: ShoeRepository,
    private val wardrobeRepository: WardrobeRepository
) : ViewModel() {

    val wardrobes = wardrobeRepository.getAllWardrobes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _saveResult = MutableSharedFlow<Boolean>()
    val saveResult = _saveResult.asSharedFlow()

    fun saveShoe(
        userId: Long,
        name: String,
        size: String,
        color: String,
        type: String,
        wardrobeId: Long?,
        imageUrl: String?
    ) = viewModelScope.launch {
        try {
            val shoe = ShoeEntity(
                userId = userId,
                name = name,
                size = size,
                color = color,
                type = type,
                wardrobeId = wardrobeId,
                imageUrl = imageUrl
            )
            shoeRepository.insert(shoe)
            _saveResult.emit(true)
        } catch (e: Exception) {
            _saveResult.emit(false)
        }
    }
} 