package edu.ucne.joseestrellarojas_ap2_p1.prentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ListEntradasScreen(viewModel: EntradaHuacalesViewModel, navController: NavHostController) {
    val entradas by viewModel.entradas.collectAsState()
    val conteo by viewModel.conteo.collectAsState()
    val total by viewModel.total.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("form") }) {
            Text("➕ Nueva Entrada")
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(entradas) { entrada ->
                Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Cliente: ${entrada.nombreCliente}")
                        Text("Fecha: ${entrada.fecha}")
                        Text("Cantidad: ${entrada.cantidad}")
                        Text("Precio: ${entrada.precio}")
                        Text("Total: ${entrada.cantidad * entrada.precio}")
                        Row {
                            Button(onClick = { viewModel.eliminar(entrada) }) { Text("Eliminar") }
                            Spacer(Modifier.width(8.dp))
                            Button(onClick = { navController.navigate("form?id=${entrada.idEntrada}") }) { Text("Editar") }
                        }
                    }
                }
            }
        }

        Divider()
        Text("Total registros: $conteo")
        Text("Monto total: $total")
    }
}
