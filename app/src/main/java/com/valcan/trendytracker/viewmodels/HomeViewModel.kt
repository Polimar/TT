package com.valcan.trendytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valcan.trendytracker.data.repository.UserRepository
import com.valcan.trendytracker.data.repository.ClothingRepository
import com.valcan.trendytracker.data.repository.ShoeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val clothingRepository: ClothingRepository,
    private val shoeRepository: ShoeRepository
) : ViewModel() {

    val users = userRepository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getClothesCount(userId: Long) = clothingRepository.getClothesCountForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun getShoesCount(userId: Long) = shoeRepository.getShoesCountForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)
} 