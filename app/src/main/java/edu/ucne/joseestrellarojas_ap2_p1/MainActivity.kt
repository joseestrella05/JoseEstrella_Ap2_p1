package edu.ucne.joseestrellarojas_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app_db"
            ).build()

            val repository = EntradaHuacalesRepositoryImpl(db.entradaHuacalesDao())

            val factory = EntradaHuacalesViewModelFactory(
                InsertEntradaUseCase(repository),
                UpdateEntradaUseCase(repository),
                DeleteEntradaUseCase(repository),
                GetEntradasUseCase(repository),
                FilterEntradasUseCase(repository),
                CountEntradasUseCase(repository),
                TotalEntradasUseCase(repository)
            )

            val navController = rememberNavController()
            val viewModel: EntradaHuacalesViewModel = viewModel(factory = factory)

            MyApp(navController, viewModel)
        }
    }
}

