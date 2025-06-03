package com.ebc.vetexpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.ebc.vetexpress.data.AppDatabase
import com.ebc.vetexpress.data.model.Veterinaria
import com.ebc.vetexpress.repository.VeterinariaRoomRepository
import com.ebc.vetexpress.ui.screens.DirectorioScreen
import com.ebc.vetexpress.ui.screens.DogImageScreen
import com.ebc.vetexpress.ui.theme.VetExpressTheme
import com.ebc.vetexpress.viewmodel.VeterinariaViewModel
import com.ebc.vetexpress.viewmodel.VeterinariaViewModelFactory
import com.ebc.vetexpress.ui.screens.DetalleVeterinariaScreen
import androidx.compose.material.icons.filled.Person
import com.ebc.vetexpress.ui.screens.PerfilScreen
import com.ebc.vetexpress.ui.screens.EditarVeterinariaScreen


class MainActivity : ComponentActivity() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { VeterinariaRoomRepository(database.veterinariaDao()) }
    private val viewModel: VeterinariaViewModel by viewModels {
        VeterinariaViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // AGREGAR VETERINARIA DE PRUEBA (solo una vez)
        viewModel.agregarVeterinaria(
            Veterinaria(
                nombre = "Vet Felina",
                direccion = "Av. Patitas 123",
                telefono = "4441234567",
                servicios = "Consulta, Vacunas"
            )
        )

        setContent {
            VetExpressTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = navController.currentDestination?.route == "directorio",
                                onClick = { navController.navigate("directorio") },
                                label = { Text("Directorio") },
                                icon = { Icon(Icons.Filled.List, contentDescription = null) }
                            )
                            NavigationBarItem(
                                selected = navController.currentDestination?.route == "perrito",
                                onClick = { navController.navigate("perrito") },
                                label = { Text("Perrito") },
                                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) }
                            )
                            NavigationBarItem(
                                selected = navController.currentDestination?.route == "perfil",
                                onClick = { navController.navigate("perfil") },
                                label = { Text("Perfil") },
                                icon = { Icon(Icons.Filled.Person, contentDescription = null) }
                            )
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "directorio",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("directorio") {
                            DirectorioScreen(
                                viewModel = viewModel,
                                onVeterinariaClick = { selectedVet, editar ->
                                    navController.currentBackStackEntry?.savedStateHandle?.set("veterinaria", selectedVet)
                                    navController.navigate(if (editar) "editar" else "detalle")
                                }
                            )
                        }

                        composable("detalle") {
                            val vet = navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<Veterinaria>("veterinaria")

                            vet?.let {
                                DetalleVeterinariaScreen(vet = it)
                            }
                        }

                        composable("perrito") {
                            DogImageScreen()
                        }

                        composable("perfil") {
                            PerfilScreen()
                        }
                        composable("editar") {
                            val vet = navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<Veterinaria>("veterinaria")

                            vet?.let {
                                EditarVeterinariaScreen(
                                    vet = it,
                                    viewModel = viewModel,
                                    onGuardar = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


