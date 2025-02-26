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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.valcan.trendytracker.R
import com.valcan.trendytracker.ui.theme.Colors
import com.valcan.trendytracker.ui.theme.Icons

data class User(
    val id: String,
    val name: String,
    val isMale: Boolean,
    val clothesCount: Int = 0,
    val shoesCount: Int = 0
)

@Composable
fun HomeScreen(navController: NavController) {
    var selectedUser by remember { mutableStateOf<User?>(null) }
    var showUserSelection by remember { mutableStateOf(true) }
    
    // Lista di esempio degli utenti
    val users = remember {
        listOf(
            User("1", "Marco", true),
            User("2", "Anna", false),
            User("3", "Luca", true),
            User("4", "Sofia", false)
        )
    }

    if (showUserSelection && selectedUser == null) {
        UserSelectionDialog(
            users = users,
            onUserSelected = { user ->
                selectedUser = user
                showUserSelection = false
            }
        )
    } else {
        HomeContent(selectedUser!!)
    }
}

@Composable
fun UserSelectionDialog(
    users: List<User>,
    onUserSelected: (User) -> Unit
) {
    Dialog(
        onDismissRequest = { /* Non permettiamo di chiudere il dialog */ }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Seleziona il tuo profilo",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(users) { user ->
                        UserSelectionItem(user, onUserSelected)
                    }
                }
            }
        }
    }
}

@Composable
fun UserSelectionItem(user: User, onUserSelected: (User) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onUserSelected(user) }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                id = if (user.isMale) R.drawable.usermale else R.drawable.userfemale
            ),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = user.name,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        
        Image(
            painter = painterResource(id = R.drawable.ic_confirm),
            contentDescription = "Confirm",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun HomeContent(user: User) {
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
                .background(Colors.PastelPink.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = Icons.CLOTHES),
                    contentDescription = "Vestiti",
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "${user.clothesCount}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Cerchio scarpe
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(Colors.PastelBlue.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = Icons.SHOES),
                    contentDescription = "Scarpe",
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "${user.shoesCount}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
} 