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

    var fechaError by remember { mutableStateOf<String?>(null) }
    var clienteError by remember { mutableStateOf<String?>(null) }
    var cantidadError by remember { mutableStateOf<String?>(null) }
    var precioError by remember { mutableStateOf<String?>(null) }

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
            // Fecha
            OutlinedTextField(
                value = fecha,
                onValueChange = {
                    fecha = it
                    fechaError = null
                },
                label = { Text("Fecha (YYYY-MM-DD)") },
                isError = fechaError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (fechaError != null) {
                Text(fechaError!!, color = MaterialTheme.colorScheme.error)
            }

            // Cliente
            OutlinedTextField(
                value = cliente,
                onValueChange = {
                    cliente = it
                    clienteError = null
                },
                label = { Text("Nombre del Cliente") },
                isError = clienteError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (clienteError != null) {
                Text(clienteError!!, color = MaterialTheme.colorScheme.error)
            }

            // Cantidad
            OutlinedTextField(
                value = cantidad,
                onValueChange = {
                    cantidad = it
                    cantidadError = null
                },
                label = { Text("Cantidad") },
                isError = cantidadError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (cantidadError != null) {
                Text(cantidadError!!, color = MaterialTheme.colorScheme.error)
            }

            // Precio
            OutlinedTextField(
                value = precio,
                onValueChange = {
                    precio = it
                    precioError = null
                },
                label = { Text("Precio") },
                isError = precioError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (precioError != null) {
                Text(precioError!!, color = MaterialTheme.colorScheme.error)
            }

            // Importe (readonly)
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
                        var valid = true

                        // Validaciones
                        if (fecha.isBlank()) {
                            fechaError = "La fecha es obligatoria"
                            valid = false
                        }
                        if (cliente.isBlank()) {
                            clienteError = "El cliente es obligatorio"
                            valid = false
                        }
                        val cantidadInt = cantidad.toIntOrNull()
                        if (cantidadInt == null || cantidadInt <= 0) {
                            cantidadError = "Debe ser un número mayor a 0"
                            valid = false
                        }
                        val precioDouble = precio.toDoubleOrNull()
                        if (precioDouble == null || precioDouble <= 0) {
                            precioError = "Debe ser un número mayor a 0"
                            valid = false
                        }

                        if (valid) {
                            val entrada = EntradaHuacales(
                                idEntrada = editar?.idEntrada ?: 0,
                                fecha = fecha,
                                nombreCliente = cliente,
                                cantidad = cantidadInt!!,
                                precio = precioDouble!!
                            )
                            if (editar == null) viewModel.insertar(entrada)
                            else viewModel.actualizar(entrada)

                            navController.popBackStack()
                        }
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
