package com.ebc.vetexpress.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {
    private val _nombre = MutableStateFlow("Juan Pérez")
    private val _correo = MutableStateFlow("juanperez@example.com")

    val nombre: StateFlow<String> = _nombre
    val correo: StateFlow<String> = _correo

    fun cerrarSesion() {
        println("🔐 Cerrar sesión ejecutado (simulado)")
    }

    fun abrirAyuda() {
        println("🆘 Ayuda solicitada (simulada)")
    }

    fun mostrarNotificaciones() {
        println("🔔 Notificaciones abiertas (simulado)")
    }
}
