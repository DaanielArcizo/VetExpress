package com.ebc.vetexpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ebc.vetexpress.R
import com.ebc.vetexpress.viewmodel.PerfilViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PerfilScreen(
    navController: NavController,
    viewModel: PerfilViewModel = viewModel()
) {
    val nombre by viewModel.nombre.collectAsState()
    val correo by viewModel.correo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_vetexpress),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp)
        )

        Text(nombre, style = MaterialTheme.typography.headlineSmall)
        Text(correo, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.cerrarSesion()
        }) {
            Text("Cerrar Sesión")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            PerfilOpcionItem("Notificaciones") { viewModel.mostrarNotificaciones() }
            PerfilOpcionItem("Ayuda") { viewModel.abrirAyuda() }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.navigate("agregar")
        }) {
            Text("Agregar veterinaria")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("VetExpress", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun PerfilOpcionItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() }
    )
}

