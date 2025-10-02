package edu.ucne.joseestrellarojas_ap2_p1.prentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEntradasScreen(
    viewModel: EntradaHuacalesViewModel,
    navController: NavHostController
) {
    val entradas by viewModel.entradas.collectAsState()
    val conteo by viewModel.conteo.collectAsState()
    val total by viewModel.total.collectAsState()

    var clienteFiltro by remember { mutableStateOf("") }
    var fechaFiltro by remember { mutableStateOf("") }
    var filtrosVisibles by remember { mutableStateOf(false) }

    LaunchedEffect(clienteFiltro, fechaFiltro) {
        if (clienteFiltro.isBlank() && fechaFiltro.isBlank()) {
            viewModel.cargarEntradas()
        } else {
            viewModel.filtrar(
                cliente = clienteFiltro.ifBlank { null },
                fecha = fechaFiltro.ifBlank { null }
            )
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Entradas de Huacales") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("form") }) {
                Text("+")
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$conteo")
                    Text("= $${"%,.2f".format(total)}")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { filtrosVisibles = !filtrosVisibles }
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Filtros", style = MaterialTheme.typography.titleMedium)
                        Icon(Icons.Default.FilterList, contentDescription = "Filtro")
                    }

                    if (filtrosVisibles) {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = clienteFiltro,
                                onValueChange = { clienteFiltro = it },
                                label = { Text("Cliente") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = fechaFiltro,
                                onValueChange = { fechaFiltro = it },
                                label = { Text("Fecha (YYYY-MM-DD)") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(entradas) { entrada ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("form/${entrada.idEntrada}")
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(entrada.nombreCliente, style = MaterialTheme.typography.bodyLarge)
                                Text("${entrada.cantidad} x $${entrada.precio}", style = MaterialTheme.typography.bodyMedium)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(entrada.fecha, style = MaterialTheme.typography.bodySmall)
                                Text(
                                    "= $${"%,.2f".format(entrada.cantidad * entrada.precio)}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}