package com.valcan.trendytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.valcan.trendytracker.viewmodels.AggiungiVestitoViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AggiungiVestitoScreen(
    navController: NavController,
    viewModel: AggiungiVestitoViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var season by remember { mutableStateOf("") }
    var selectedWardrobeId by remember { mutableStateOf<Long?>(null) }
    val scope = rememberCoroutineScope()
    val wardrobes by viewModel.wardrobes.collectAsState()

    LaunchedEffect(viewModel.saveResult) {
        viewModel.saveResult.collect { success ->
            if (success) {
                navController.navigateUp()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Aggiungi Vestito") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Indietro")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = type,
                onValueChange = { type = it },
                label = { Text("Tipo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Colore") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = season,
                onValueChange = { season = it },
                label = { Text("Stagione") },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = { }
            ) {
                OutlinedTextField(
                    value = wardrobes.find { it.id == selectedWardrobeId }?.name ?: "",
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Armadio") },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { }
                ) {
                    wardrobes.forEach { wardrobe ->
                        DropdownMenuItem(
                            text = { Text(wardrobe.name) },
                            onClick = { selectedWardrobeId = wardrobe.id }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    scope.launch {
                        viewModel.saveClothing(
                            userId = 1, // TODO: Get from UserManager
                            name = name,
                            type = type,
                            color = color,
                            season = season,
                            wardrobeId = selectedWardrobeId,
                            imageUrl = null // TODO: Implement image upload
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salva")
            }
        }
    }
} 