package edu.ucne.joseestrellarojas_ap2_p1.prentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.ucne.joseestrellarojas_ap2_p1.domain.model.EntradaHuacales
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.EntradaHuacalesViewModel

@Composable
fun FormEntradaScreen(viewModel: EntradaHuacalesViewModel, navController: NavHostController, editar: EntradaHuacales? = null) {
    var fecha by remember { mutableStateOf(editar?.fecha ?: "") }
    var cliente by remember { mutableStateOf(editar?.nombreCliente ?: "") }
    var cantidad by remember { mutableStateOf(editar?.cantidad?.toString() ?: "") }
    var precio by remember { mutableStateOf(editar?.precio?.toString() ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") })
        OutlinedTextField(value = cliente, onValueChange = { cliente = it }, label = { Text("Cliente") })
        OutlinedTextField(value = cantidad, onValueChange = { cantidad = it }, label = { Text("Cantidad") })
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })

        Button(onClick = {
            val entrada = EntradaHuacales(
                idEntrada = editar?.idEntrada ?: 0,
                fecha = fecha,
                nombreCliente = cliente,
                cantidad = cantidad.toIntOrNull() ?: 0,
                precio = precio.toDoubleOrNull() ?: 0.0
            )
            if (editar == null) viewModel.insertar(entrada) else viewModel.actualizar(entrada)
            navController.popBackStack()
        }) {
            Text(if (editar == null) "Guardar" else "Actualizar")
        }
    }
}
