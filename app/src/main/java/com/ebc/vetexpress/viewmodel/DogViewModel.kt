package com.ebc.vetexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.vetexpress.data.api.RetrofitInstance
import com.ebc.vetexpress.data.model.DogImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {

    private val _dogImage = MutableStateFlow<DogImage?>(null)
    val dogImage: StateFlow<DogImage?> = _dogImage

    fun fetchRandomDog() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRandomDog()
                _dogImage.value = response
            } catch (e: Exception) {
                _dogImage.value = null
            }
        }
    }
}

