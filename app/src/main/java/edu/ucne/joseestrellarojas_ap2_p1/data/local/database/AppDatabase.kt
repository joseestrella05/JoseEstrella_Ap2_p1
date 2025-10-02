package edu.ucne.joseestrellarojas_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.joseestrellarojas_ap2_p1.data.local.entities.EntradaHuacalesEntity
import edu.ucne.joseestrellarojas_ap2_p1.data.local.dao.EntradaHuacalesDao

@Database(
    entities = [EntradaHuacalesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entradaHuacalesDao(): EntradaHuacalesDao
}