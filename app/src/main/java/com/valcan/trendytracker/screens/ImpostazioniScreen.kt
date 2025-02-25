package com.valcan.trendytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valcan.trendytracker.navigation.NavigationItem

data class SettingsItem(
    val title: String,
    val route: String
)

@Composable
fun ImpostazioniScreen(navController: NavController) {
    val settingsItems = listOf(
        SettingsItem("Utente", NavigationItem.User.route),
        SettingsItem("Armadi", NavigationItem.Armadio.route),
        SettingsItem("Aggiungi Utente M", NavigationItem.AddUserMale.route),
        SettingsItem("Aggiungi Utente F", NavigationItem.AddUserFemale.route),
        SettingsItem("Backup", NavigationItem.Backup.route),
        SettingsItem("Ripristino", NavigationItem.Restore.route)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Impostazioni",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(settingsItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            onClick = { navController.navigate(item.route) }
                        ) {
                            Text(item.title)
                        }
                    }
                }
            }
        }
    }
} 