package com.ebc.vetexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ebc.vetexpress.repository.VeterinariaRoomRepository

class VeterinariaViewModelFactory(
    private val repository: VeterinariaRoomRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VeterinariaViewModel::class.java)) {
            return VeterinariaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

