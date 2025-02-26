package com.valcan.trendytracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valcan.trendytracker.R
import com.valcan.trendytracker.data.entities.UserEntity
import com.valcan.trendytracker.navigation.NavigationItem
import com.valcan.trendytracker.viewmodels.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.foundation.clickable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val users by viewModel.users.collectAsState()
    var showGenderDialog by remember { mutableStateOf(false) }

    if (showGenderDialog) {
        SelectGenderDialog(
            onDismiss = { showGenderDialog = false },
            onGenderSelected = { isMale ->
                navController.navigate(
                    if (isMale) NavigationItem.AddUserMale.route 
                    else NavigationItem.AddUserFemale.route
                )
                showGenderDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Utenti",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (users.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Nessun utente presente")
                Button(
                    onClick = { showGenderDialog = true }
                ) {
                    Text("Crea Primo Utente")
                }
            }
        } else {
            LazyColumn {
                items(users) { user ->
                    UserItem(
                        user = user,
                        onUserClick = { viewModel.selectUser(user) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { showGenderDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Aggiungi Nuovo Utente")
            }
        }
    }
}

@Composable
fun UserItem(
    user: UserEntity,
    onUserClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onUserClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = if (user.isMale) R.drawable.ic_male else R.drawable.ic_female
                ),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(user.name, style = MaterialTheme.typography.titleMedium)
                // Aggiungi qui la data di nascita quando la implementerai
            }
        }
    }
} 