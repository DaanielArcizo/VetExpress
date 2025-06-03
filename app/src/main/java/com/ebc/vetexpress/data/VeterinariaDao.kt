package com.ebc.vetexpress.data

import androidx.room.*
import com.ebc.vetexpress.data.model.Veterinaria
import kotlinx.coroutines.flow.Flow

@Dao
interface VeterinariaDao {
    @Query("SELECT * FROM veterinarias")
    fun obtenerTodas(): Flow<List<Veterinaria>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(veterinaria: Veterinaria)

    @Query("DELETE FROM veterinarias")
    suspend fun eliminarTodo()
}


