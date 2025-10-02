package edu.ucne.joseestrellarojas_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import edu.ucne.joseestrellarojas_ap2_p1.data.local.database.AppDatabase
import edu.ucne.joseestrellarojas_ap2_p1.data.repository.EntradaHuacalesRepositoryImpl
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.CountEntradasUseCase
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.DeleteEntradaUseCase
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.FilterEntradasUseCase
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.GetEntradasUseCase
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.InsertEntradaUseCase
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.TotalEntradasUseCase
import edu.ucne.joseestrellarojas_ap2_p1.domain.usecase.UpdateEntradaUseCase
import edu.ucne.joseestrellarojas_ap2_p1.prentation.edit.FormEntradaScreen
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.EntradaHuacalesViewModel
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.EntradaHuacalesViewModelFactory
import edu.ucne.joseestrellarojas_ap2_p1.prentation.list.ListEntradasScreen
import edu.ucne.joseestrellarojas_ap2_p1.ui.theme.JoseEstrellaRojas_AP2_P1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_db"
    ).build()

    val repository = EntradaHuacalesRepositoryImpl(db.entradaHuacalesDao())

    // Instanciamos los UseCases
    val insertEntrada = InsertEntradaUseCase(repository)
    val updateEntrada = UpdateEntradaUseCase(repository)
    val deleteEntrada = DeleteEntradaUseCase(repository)
    val getEntradas = GetEntradasUseCase(repository)
    val filterEntradas = FilterEntradasUseCase(repository)
    val countEntradas = CountEntradasUseCase(repository)
    val totalEntradas = TotalEntradasUseCase(repository)

    val factory = EntradaHuacalesViewModelFactory(
        insertEntrada,
        updateEntrada,
        deleteEntrada,
        getEntradas,
        filterEntradas,
        countEntradas,
        totalEntradas
    )

    val viewModel: EntradaHuacalesViewModel = viewModel(factory = factory)

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Control de Huacales") }) }
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
                FormEntradaScreen(viewModel, navController)
            }
        }
    }
}