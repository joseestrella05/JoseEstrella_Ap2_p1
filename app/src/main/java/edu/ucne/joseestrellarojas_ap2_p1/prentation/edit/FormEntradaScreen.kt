package edu.ucne.joseestrellarojas_ap2_p1.prentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.ucne.joseestrellarojas_ap2_p1.domain.model.EntradaHuacales
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.EntradaHuacalesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEntradaScreen(
    viewModel: EntradaHuacalesViewModel,
    navController: NavHostController,
    editar: EntradaHuacales? = null
) {
    var fecha by remember { mutableStateOf(editar?.fecha ?: "") }
    var cliente by remember { mutableStateOf(editar?.nombreCliente ?: "") }
    var cantidad by remember { mutableStateOf(editar?.cantidad?.toString() ?: "") }
    var precio by remember { mutableStateOf(editar?.precio?.toString() ?: "") }

    val importe = (cantidad.toIntOrNull() ?: 0) * (precio.toDoubleOrNull() ?: 0.0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (editar == null) "Nueva Entrada" else "Editar Entrada") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cliente,
                onValueChange = { cliente = it },
                label = { Text("Nombre del Cliente") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = importe.toString(),
                onValueChange = {},
                enabled = false,
                label = { Text("Importe") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val entrada = EntradaHuacales(
                            idEntrada = editar?.idEntrada ?: 0,
                            fecha = fecha,
                            nombreCliente = cliente,
                            cantidad = cantidad.toIntOrNull() ?: 0,
                            precio = precio.toDoubleOrNull() ?: 0.0
                        )
                        if (editar == null) viewModel.insertar(entrada)
                        else viewModel.actualizar(entrada)

                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Guardar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                if (editar != null) {
                    OutlinedButton(
                        onClick = {
                            viewModel.eliminar(editar)
                            navController.popBackStack()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Eliminar")
                    }
                }
            }

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}