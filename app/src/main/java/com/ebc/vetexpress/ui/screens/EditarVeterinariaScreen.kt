package com.ebc.vetexpress.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ebc.vetexpress.data.model.Veterinaria
import com.ebc.vetexpress.viewmodel.VeterinariaViewModel

@Composable
fun EditarVeterinariaScreen(
    vet: Veterinaria,
    viewModel: VeterinariaViewModel,
    onGuardar: () -> Unit
) {
    var nombre by remember { mutableStateOf(vet.nombre) }
    var direccion by remember { mutableStateOf(vet.direccion) }
    var telefono by remember { mutableStateOf(vet.telefono) }
    var servicios by remember { mutableStateOf(vet.servicios) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Editar Veterinaria", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = servicios,
            onValueChange = { servicios = it },
            label = { Text("Servicios") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val veterinariaEditada = vet.copy(
                nombre = nombre,
                direccion = direccion,
                telefono = telefono,
                servicios = servicios
            )
            viewModel.agregarVeterinaria(veterinariaEditada)
            onGuardar()
        }) {
            Text("Guardar Cambios")
        }
    }
}
