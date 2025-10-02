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
                    Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total registros: $conteo")
                    Text("= $${"%,.2f".format(total)}")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Filtros")
                Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filtrar")
            }

            Spacer(Modifier.height(8.dp))

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
