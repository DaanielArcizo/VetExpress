package com.ebc.vetexpress.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ebc.vetexpress.viewmodel.VeterinariaViewModel
import androidx.compose.runtime.collectAsState



@Composable
fun DirectorioScreen(viewModel: VeterinariaViewModel) {
    val lista by viewModel.veterinarias.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Directorio de Veterinarias",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(lista) { vet ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            Toast.makeText(
                                context,
                                "Seleccionaste: ${vet.nombre}",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = vet.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = vet.direccion)
                        Text(text = "Tel: ${vet.telefono}")
                        Text(text = "Servicios: ${vet.servicios}")
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(vet.direccion)}")
                                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                    mapIntent.setPackage("com.google.android.apps.maps")
                                    if (mapIntent.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(mapIntent)
                                    } else {
                                        Toast.makeText(context, "No se pudo abrir Google Maps", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) {
                                Text("Ver en mapa")
                            }

                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL)
                                    intent.data = Uri.parse("tel:${vet.telefono}")
                                    context.startActivity(intent)
                                }
                            ) {
                                Text("Llamar")
                            }
                        }
                    }
                }
            }
        }
    }
}

