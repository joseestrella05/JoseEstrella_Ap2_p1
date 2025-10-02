package edu.ucne.joseestrellarojas_ap2_p1.prentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.*

class EntradaHuacalesViewModelFactory(
    private val insertEntrada: InsertEntradaUseCase,
    private val updateEntrada: UpdateEntradaUseCase,
    private val deleteEntrada: DeleteEntradaUseCase,
    private val getEntradas: GetEntradasUseCase,
    private val filterEntradas: FilterEntradasUseCase,
    private val countEntradas: CountEntradasUseCase,
    private val totalEntradas: TotalEntradasUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntradaHuacalesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntradaHuacalesViewModel(
                insertEntrada,
                updateEntrada,
                deleteEntrada,
                getEntradas,
                filterEntradas,
                countEntradas,
                totalEntradas
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
