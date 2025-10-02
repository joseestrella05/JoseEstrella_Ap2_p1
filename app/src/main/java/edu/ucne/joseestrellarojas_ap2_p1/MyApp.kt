package edu.ucne.joseestrellarojas_ap2_p1

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.joseestrellarojas_ap2_p1.prentation.edit.FormEntradaScreen
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.EntradaHuacalesViewModel
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.ListEntradasScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(navController: NavHostController, viewModel: EntradaHuacalesViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Control de Huacales") }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "lista",
            modifier = Modifier.padding(padding)
        ) {
            composable("lista") {
                ListEntradasScreen(viewModel, navController)
            }

            composable("form") {
                FormEntradaScreen(viewModel, navController, null)
            }

            composable("form/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                val entradas by viewModel.entradas.collectAsState()
                val editar = entradas.find { it.idEntrada == id }

                FormEntradaScreen(viewModel, navController, editar)
            }
        }
    }
}
