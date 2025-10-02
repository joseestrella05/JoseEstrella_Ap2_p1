package edu.ucne.joseestrellarojas_ap2_p1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class EntradaHuacales(
    val idEntrada: Int = 0,
    val fecha: String,
    val nombreCliente: String,
    val cantidad: Int,
    val precio: Double
)
