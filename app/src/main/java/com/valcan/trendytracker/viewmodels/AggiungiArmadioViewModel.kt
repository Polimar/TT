package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.entities.WardrobeEntity
import com.valcan.trendytracker.data.repository.WardrobeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AggiungiArmadioViewModel @Inject constructor(
    private val wardrobeRepository: WardrobeRepository
) : ViewModel() {

    private val _saveResult = MutableSharedFlow<Boolean>()
    val saveResult = _saveResult.asSharedFlow()

    fun saveWardrobe(
        name: String,
        location: String,
        description: String?
    ) = viewModelScope.launch {
        try {
            val wardrobe = WardrobeEntity(
                name = name,
                location = location,
                description = description
            )
            wardrobeRepository.insert(wardrobe)
            _saveResult.emit(true)
        } catch (e: Exception) {
            _saveResult.emit(false)
        }
    }
} 