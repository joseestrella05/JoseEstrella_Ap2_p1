package edu.ucne.joseestrellarojas_ap2_p1.prentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.joseestrellarojas_ap2_p1.domain.model.EntradaHuacales
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.*

class EntradaHuacalesViewModel(
    private val insertEntrada: InsertEntradaUseCase,
    private val updateEntrada: UpdateEntradaUseCase,
    private val deleteEntrada: DeleteEntradaUseCase,
    private val getEntradas: GetEntradasUseCase,
    private val filterEntradas: FilterEntradasUseCase,
    private val countEntradas: CountEntradasUseCase,
    private val totalEntradas: TotalEntradasUseCase
) : ViewModel() {

    private val _entradas = MutableStateFlow<List<EntradaHuacales>>(emptyList())
    val entradas: StateFlow<List<EntradaHuacales>> = _entradas

    private val _conteo = MutableStateFlow(0)
    val conteo: StateFlow<Int> = _conteo

    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total

    fun cargarEntradas() {
        viewModelScope.launch {
            _entradas.value = getEntradas()
            _conteo.value = countEntradas()
            _total.value = totalEntradas()
        }
    }

    fun insertar(e: EntradaHuacales) {
        viewModelScope.launch {
            insertEntrada(e)
            cargarEntradas()
        }
    }

    fun actualizar(e: EntradaHuacales) {
        viewModelScope.launch {
            updateEntrada(e)
            cargarEntradas()
        }
    }

    fun eliminar(e: EntradaHuacales) {
        viewModelScope.launch {
            deleteEntrada(e)
            cargarEntradas()
        }
    }

    fun filtrar(cliente: String?, fecha: String?) {
        viewModelScope.launch {
            _entradas.value = filterEntradas(cliente, fecha)
            _conteo.value = _entradas.value.size
            _total.value = _entradas.value.sumOf { it.cantidad * it.precio }
        }
    }
}
