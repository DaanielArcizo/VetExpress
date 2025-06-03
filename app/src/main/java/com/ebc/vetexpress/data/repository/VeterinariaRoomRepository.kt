package com.ebc.vetexpress.repository

import com.ebc.vetexpress.data.VeterinariaDao
import com.ebc.vetexpress.data.model.Veterinaria


class VeterinariaRoomRepository(private val dao: VeterinariaDao) {
    val todas = dao.obtenerTodas()

    suspend fun insertar(vet: Veterinaria) {
        dao.insertar(vet)
    }

    suspend fun eliminarTodo() {
        dao.eliminarTodo()
    }
}

