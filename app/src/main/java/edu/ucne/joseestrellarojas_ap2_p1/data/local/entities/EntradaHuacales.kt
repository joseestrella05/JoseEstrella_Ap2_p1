package edu.ucne.joseestrellarojas_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entradas_huacales")
data class EntradaHuacalesEntity(
    @PrimaryKey(autoGenerate = true) val idEntrada: Int = 0,
    val fecha: String,
    val nombreCliente: String,
    val cantidad: Int,
    val precio: Double
)
