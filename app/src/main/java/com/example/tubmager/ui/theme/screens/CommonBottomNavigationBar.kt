package com.example.tubmager.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonBottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "Beranda",
                    tint = if (currentRoute == "home") Color(0xFF4FC3F7) else Color.Gray
                )
            },
            label = {
                Text(
                    "Beranda",
                    color = if (currentRoute == "home") Color(0xFF4FC3F7) else Color.Gray
                )
            },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4FC3F7),
                selectedTextColor = Color(0xFF4FC3F7),
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.History,
                    contentDescription = "Riwayat",
                    tint = if (currentRoute == "riwayat") Color(0xFF4FC3F7) else Color.Gray
                )
            },
            label = {
                Text(
                    "Riwayat",
                    color = if (currentRoute == "riwayat") Color(0xFF4FC3F7) else Color.Gray
                )
            },
            selected = currentRoute == "riwayat",
            onClick = {
                navController.navigate("riwayat") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4FC3F7),
                selectedTextColor = Color(0xFF4FC3F7),
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.AccountCircle,
                    contentDescription = "Profil",
                    tint = if (currentRoute == "profil") Color(0xFF4FC3F7) else Color.Gray
                )
            },
            label = {
                Text(
                    "Profil",
                    color = if (currentRoute == "profil") Color(0xFF4FC3F7) else Color.Gray
                )
            },
            selected = currentRoute == "profil",
            onClick = {
                navController.navigate("profil") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4FC3F7),
                selectedTextColor = Color(0xFF4FC3F7),
                indicatorColor = Color.Transparent
            )
        )
    }
}