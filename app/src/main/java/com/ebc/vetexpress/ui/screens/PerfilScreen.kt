package com.ebc.vetexpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ebc.vetexpress.R

@Composable
fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.logo_vetexpress),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp)
        )

        // Nombre y correo
        Text("Juan Pérez", style = MaterialTheme.typography.headlineSmall)
        Text("juanperez@example.com", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de cerrar sesión (simulado)
        Button(onClick = {
            // Aquí podrías hacer logout o simularlo
        }) {
            Text("Cerrar Sesión")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Opciones de navegación simuladas
        Column(modifier = Modifier.fillMaxWidth()) {
            PerfilOpcionItem(texto = "Veterinarias guardadas")
            PerfilOpcionItem(texto = "Notificaciones")
            PerfilOpcionItem(texto = "Ayuda")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text("VetExpress", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun PerfilOpcionItem(texto: String) {
    Text(
        text = texto,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    )
}
