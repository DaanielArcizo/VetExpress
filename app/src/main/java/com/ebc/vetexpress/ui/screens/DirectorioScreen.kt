package com.ebc.vetexpress.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.ebc.vetexpress.viewmodel.VeterinariaViewModel
import com.ebc.vetexpress.R
import com.ebc.vetexpress.data.model.Veterinaria
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.Icons
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.*


@Composable
fun DirectorioScreen(
    viewModel: VeterinariaViewModel,
    onVeterinariaClick: (Veterinaria, Boolean) -> Unit
) {
    val lista by viewModel.veterinarias.collectAsState()
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_vetexpress),
            contentDescription = "Logo VetExpress",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Directorio de Veterinarias",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar veterinarias") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        val listaFiltrada = lista
            .filter {
                it.nombre.contains(query, ignoreCase = true) ||
                        it.servicios.contains(query, ignoreCase = true)
            }
            .sortedByDescending { it.esFavorita }

        LazyColumn {
            items(listaFiltrada) { vet ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onVeterinariaClick(vet, false) },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = MaterialTheme.shapes.large,
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    )  {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = vet.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "📍 ${vet.direccion}")
                        Text(text = "📞 ${vet.telefono}")
                        Text(text = "📋 ${vet.servicios}")
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
                            Button(
                                onClick = {
                                    onVeterinariaClick(vet, true)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                            ) {
                                Text("Editar")
                            }
                            IconButton(onClick = {
                                val nuevaVet = vet.copy(esFavorita = !vet.esFavorita)
                                viewModel.agregarVeterinaria(nuevaVet)
                            }) {
                                Icon(
                                    imageVector = if (vet.esFavorita) Icons.Filled.Star else Icons.Filled.StarBorder,
                                    contentDescription = "Favorita"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
}
