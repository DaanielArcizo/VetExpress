package com.ebc.vetexpress.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "veterinarias")
data class Veterinaria(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val servicios: String,
    val esFavorita: Boolean = false
) : Serializable


