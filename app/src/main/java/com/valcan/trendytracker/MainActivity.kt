package com.valcan.trendytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.valcan.trendytracker.navigation.NavigationItem
import com.valcan.trendytracker.ui.theme.TrendyTrackerTheme
import com.valcan.trendytracker.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrendyTrackerTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            listOf(
                                NavigationItem.Home,
                                NavigationItem.Vestiti,
                                NavigationItem.Scarpe,
                                NavigationItem.Cerca,
                                NavigationItem.Impostazioni
                            ).forEach { item ->
                                NavigationBarItem(
                                    selected = currentRoute == item.route,
                                    onClick = { navController.navigate(item.route) },
                                    icon = { /* Qui andrà l'icona */ },
                                    label = { Text(item.route.capitalize()) }
                                )
                            }
                        }
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
        }
    }
}