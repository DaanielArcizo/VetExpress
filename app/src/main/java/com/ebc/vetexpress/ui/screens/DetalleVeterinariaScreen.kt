package com.ebc.vetexpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.ebc.vetexpress.R
import com.ebc.vetexpress.data.model.Veterinaria

@Composable
fun DetalleVeterinariaScreen(vet: Veterinaria) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = vet.nombre,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_vetexpress),
            contentDescription = "Imagen de la veterinaria",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Dirección: ${vet.direccion}")
        Text("Teléfono: ${vet.telefono}")
        Text("Servicios: ${vet.servicios}")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ubicación aproximada:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(4.dp)
        ) {
            Text("🗺️ Mapa aquí (simulado)", modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.Center))
        }
    }
}

