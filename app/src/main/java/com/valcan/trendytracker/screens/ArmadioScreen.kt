package com.valcan.trendytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valcan.trendytracker.navigation.NavigationItem

@Composable
fun ArmadioScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavigationItem.AggiungiArmadio.route) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Aggiungi Armadio")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "I tuoi Armadi",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            
            // Lista armadi (da implementare)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Armadio Camera",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Vestiti: 0, Scarpe: 0",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
} 