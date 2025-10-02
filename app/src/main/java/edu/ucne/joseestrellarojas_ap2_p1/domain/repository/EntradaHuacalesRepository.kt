package edu.ucne.joseestrellarojas_ap2_p1.domain.repository

import edu.ucne.joseestrellarojas_ap2_p1.data.local.mapper.toDomain
import edu.ucne.joseestrellarojas_ap2_p1.data.local.mapper.toEntity
import edu.ucne.joseestrellarojas_ap2_p1.domain.model.EntradaHuacales

interface EntradaHuacalesRepository {
    suspend fun insertar(e: EntradaHuacales)
    suspend fun actualizar(e: EntradaHuacales)
    suspend fun eliminar(e: EntradaHuacales)
    suspend fun listar(): List<EntradaHuacales>
    suspend fun filtrar(cliente: String?, fecha: String?): List<EntradaHuacales>
    suspend fun contar(): Int
    suspend fun total(): Double
}