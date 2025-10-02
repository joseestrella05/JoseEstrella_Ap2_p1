package edu.ucne.joseestrellarojas_ap2_p1.data.local.dao

import androidx.room.*
import edu.ucne.joseestrellarojas_ap2_p1.data.local.entities.EntradaHuacalesEntity

@Dao
interface EntradaHuacalesDao {
    @Insert suspend fun insertar(entrada: EntradaHuacalesEntity)
    @Update suspend fun actualizar(entrada: EntradaHuacalesEntity)
    @Delete suspend fun eliminar(entrada: EntradaHuacalesEntity)

    @Query("SELECT * FROM entradas_huacales")
    suspend fun listar(): List<EntradaHuacalesEntity>

    @Query("SELECT * FROM entradas_huacales WHERE (:cliente IS NULL OR nombreCliente LIKE '%' || :cliente || '%') AND (:fecha IS NULL OR fecha LIKE '%' || :fecha || '%')")
    suspend fun filtrar(cliente: String?, fecha: String?): List<EntradaHuacalesEntity>

    @Query("SELECT COUNT(*) FROM entradas_huacales")
    suspend fun contar(): Int

    @Query("SELECT SUM(cantidad * precio) FROM entradas_huacales")
    suspend fun total(): Double?
}