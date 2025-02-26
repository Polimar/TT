package com.valcan.trendytracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.valcan.trendytracker.R
import com.valcan.trendytracker.ui.theme.Colors
import com.valcan.trendytracker.navigation.NavigationItem
import com.valcan.trendytracker.viewmodels.UserViewModel
import com.valcan.trendytracker.data.entities.UserEntity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val selectedUser by viewModel.selectedUser.collectAsState()
    val users by viewModel.users.collectAsState()
    var showGenderDialog by remember { mutableStateOf(false) }
    var showUserSelectionDialog by remember { mutableStateOf(false) }

    LaunchedEffect(users, selectedUser) {
        when {
            users.isEmpty() -> {
                showGenderDialog = true
            }
            users.size == 1 && selectedUser == null -> {
                viewModel.selectUser(users.first())
            }
            selectedUser == null && users.size > 1 -> {
                showUserSelectionDialog = true
            }
        }
    }

    if (showGenderDialog) {
        SelectGenderDialog(
            onDismiss = { showGenderDialog = false },
            onGenderSelected = { isMale ->
                navController.navigate(
                    route = if (isMale) NavigationItem.AddUserMale.route 
                           else NavigationItem.AddUserFemale.route
                )
                showGenderDialog = false
            }
        )
    }
    
    if (showUserSelectionDialog && users.size > 1) {
        UserSelectionDialog(
            users = users,
            onUserSelected = { user ->
                viewModel.selectUser(user)
                showUserSelectionDialog = false
            }
        )
    }

    selectedUser?.let { user ->
        HomeContent(
            user = user,
            onVestitiClick = { navController.navigate(NavigationItem.Vestiti.route) },
            onScarpeClick = { navController.navigate(NavigationItem.Scarpe.route) }
        )
    }
}

@Composable
fun UserSelectionDialog(
    users: List<UserEntity>,
    onUserSelected: (UserEntity) -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = { 
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Benvenuto in TrendyTracker",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { onUserSelected(user) }
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (user.isMale) R.drawable.ic_male 
                                else R.drawable.ic_female
                            ),
                            contentDescription = "User Avatar",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(Colors.PastelBlue.copy(alpha = 0.7f))
                                .padding(8.dp)
                        )
                        Text(
                            text = user.name,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        },
        confirmButton = { }
    )
}

@Composable
fun HomeContent(
    user: UserEntity,
    onVestitiClick: () -> Unit,
    onScarpeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ciao ${user.name}",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Text(
            text = "Cosa ci mettiamo oggi?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Cerchio vestiti
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(Colors.PastelPink.copy(alpha = 0.7f))
                .clickable(onClick = onVestitiClick),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Aggiungi",
                    modifier = Modifier.size(64.dp)
                )
                Text("Vestiti")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cerchio scarpe
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(Colors.PastelBlue.copy(alpha = 0.7f))
                .clickable(onClick = onScarpeClick),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Aggiungi",
                    modifier = Modifier.size(64.dp)
                )
                Text("Scarpe")
            }
        }
    }
} 