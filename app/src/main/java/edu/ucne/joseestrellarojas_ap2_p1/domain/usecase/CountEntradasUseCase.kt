package edu.ucne.joseestrellarojas_ap2_p1.domain.usecase

import edu.ucne.joseestrellarojas_ap2_p1.domain.repository.EntradaHuacalesRepository

class CountEntradasUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(): Int {
        return repository.contar()
    }
}