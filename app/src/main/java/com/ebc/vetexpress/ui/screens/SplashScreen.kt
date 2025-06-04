package com.ebc.vetexpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.ebc.vetexpress.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(2000) // 2 segundos
        navController.navigate("directorio") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_vetexpress),
            contentDescription = "Logo VetExpress",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text("VetExpress", style = MaterialTheme.typography.headlineMedium)
    }
}
