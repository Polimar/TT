package com.valcan.trendytracker.navigation

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem("home")
    object Vestiti : NavigationItem("vestiti")
    object Scarpe : NavigationItem("scarpe")
    object Cerca : NavigationItem("cerca")
    object Impostazioni : NavigationItem("impostazioni")
    object AggiungiVestito : NavigationItem("aggiungi_vestito")
    object AggiungiScarpa : NavigationItem("aggiungi_scarpa")
    object User : NavigationItem("user")
    object AddUserMale : NavigationItem("add_user_male") 
    object AddUserFemale : NavigationItem("add_user_female")
    object Armadio : NavigationItem("armadio")
    object AggiungiArmadio : NavigationItem("aggiungi_armadio")
    object Backup : NavigationItem("backup")
    object Restore : NavigationItem("restore")
} 