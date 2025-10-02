package edu.ucne.joseestrellarojas_ap2_p1.domain.usecase

import edu.ucne.joseestrellarojas_ap2_p1.domain.model.EntradaHuacales
import edu.ucne.joseestrellarojas_ap2_p1.domain.repository.EntradaHuacalesRepository

class DeleteEntradaUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(entrada: EntradaHuacales) {
        repository.eliminar(entrada)
    }
}