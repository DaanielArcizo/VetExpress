package com.ebc.vetexpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ebc.vetexpress.viewmodel.DogViewModel

@Composable
fun DogImageScreen(viewModel: DogViewModel = viewModel()) {
    val dog by viewModel.dogImage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Perrito del día! 🐶",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchRandomDog() }) {
            Text("Dame una imagen tierna")
        }

        Spacer(modifier = Modifier.height(24.dp))

        dog?.let {
            if (it.message.isNotEmpty()) {
                Text("URL recibida: ${it.message}")
                Image(
                    painter = rememberAsyncImagePainter(it.message),
                    contentDescription = "Perrito bonito",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            } else {
                Text("La URL está vacía o inválida.")
            }
        } ?: Text("No se recibió ninguna imagen todavía.")

    }
}
