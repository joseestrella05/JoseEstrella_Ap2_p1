package edu.ucne.joseestrellarojas_ap2_p1.domain.usecase

import edu.ucne.joseestrellarojas_ap2_p1.domain.repository.EntradaHuacalesRepository

class TotalEntradasUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(): Double {
        return repository.total()
    }
}
