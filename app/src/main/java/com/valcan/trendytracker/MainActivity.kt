package com.valcan.trendytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.valcan.trendytracker.navigation.NavigationItem
import com.valcan.trendytracker.ui.theme.*
import com.valcan.trendytracker.screens.*
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ColorFilter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrendyTrackerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = Colors.PastelPink.copy(alpha = 0.9f),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val items = listOf(
            NavigationItem.Home to Icons.HOME,
            NavigationItem.Vestiti to Icons.CLOTHES,
            NavigationItem.Scarpe to Icons.SHOES,
            NavigationItem.Cerca to Icons.SEARCH,
            NavigationItem.Impostazioni to Icons.SETTINGS
        )

        items.forEach { (item, iconRes) ->
            val selected = currentRoute == item.route
            val iconSize = if (selected) 57.6.dp else 48.dp
            
            NavigationBarItem(
                selected = selected,
                onClick = { 
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(NavigationItem.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = item.route.capitalize(),
                        modifier = Modifier
                            .size(iconSize)
                            .padding(4.dp),
                        colorFilter = if (!selected) {
                            ColorFilter.colorMatrix(
                                ColorMatrix().apply { setToSaturation(0.6f) }
                            )
                        } else null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Colors.PastelPurple,
                    unselectedIconColor = Colors.PastelBlue,
                    indicatorColor = Colors.PastelYellow.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        containerColor = Colors.PastelYellow.copy(alpha = 0.3f),
        bottomBar = {
            BottomNavigationBar(navController, currentRoute)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.Home.route) { HomeScreen(navController) }
            composable(NavigationItem.Vestiti.route) { VestitiScreen(navController) }
            composable(NavigationItem.Scarpe.route) { ScarpeScreen(navController) }
            composable(NavigationItem.Cerca.route) { CercaScreen(navController) }
            composable(NavigationItem.Impostazioni.route) { ImpostazioniScreen(navController) }
            composable(NavigationItem.AggiungiVestito.route) { AggiungiVestitoScreen(navController) }
            composable(NavigationItem.AggiungiScarpa.route) { AggiungiScarpaScreen(navController) }
            composable(NavigationItem.User.route) { UserScreen(navController) }
            composable(NavigationItem.AddUserMale.route) { AddUserMaleScreen(navController) }
            composable(NavigationItem.AddUserFemale.route) { AddUserFemaleScreen(navController) }
            composable(NavigationItem.Armadio.route) { ArmadioScreen(navController) }
            composable(NavigationItem.AggiungiArmadio.route) { AggiungiArmadioScreen(navController) }
            composable(NavigationItem.Backup.route) { BackupScreen(navController) }
            composable(NavigationItem.Restore.route) { RestoreScreen(navController) }
        }
    }
}