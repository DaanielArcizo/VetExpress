package com.ebc.vetexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.vetexpress.data.model.Veterinaria
import com.ebc.vetexpress.repository.VeterinariaRoomRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VeterinariaViewModel(private val repository: VeterinariaRoomRepository) : ViewModel() {

    val veterinarias: StateFlow<List<Veterinaria>> = repository.todas
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun agregarVeterinaria(vet: Veterinaria) {
        viewModelScope.launch {
            repository.insertar(vet)
        }
    }

    fun borrarTodo() {
        viewModelScope.launch {
            repository.eliminarTodo()
        }
    }
}
