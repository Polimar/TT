package com.valcan.trendytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.valcan.trendytracker.viewmodels.AggiungiUtenteViewModel
import com.valcan.trendytracker.navigation.NavigationItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserFemaleScreen(
    navController: NavController,
    viewModel: AggiungiUtenteViewModel = hiltViewModel()
) {
    var nome by remember { mutableStateOf("") }
    var dataNascita by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.saveResult) {
        viewModel.saveResult.collect { success ->
            if (success) {
                navController.navigate(NavigationItem.Home.route) {
                    popUpTo(NavigationItem.Home.route)
                    launchSingleTop = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nuova Utente Femminile",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dataNascita,
            onValueChange = { dataNascita = it },
            label = { Text("Data di Nascita (GG/MM/AAAA)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    viewModel.saveUser(
                        name = nome,
                        isMale = false
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nome.isNotBlank()
        ) {
            Text("Salva")
        }
    }
} 