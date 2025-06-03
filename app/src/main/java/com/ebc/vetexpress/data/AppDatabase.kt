package com.ebc.vetexpress.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ebc.vetexpress.data.model.Veterinaria

@Database(entities = [Veterinaria::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun veterinariaDao(): VeterinariaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vetexpress_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
