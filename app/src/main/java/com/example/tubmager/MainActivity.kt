package com.example.tubmager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tubmager.ui.theme.UBMagerTheme
import com.example.tubmager.ui.theme.screens.*

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UBMagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UBMagerApp()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun UBMagerApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") { OnboardingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("jastip") { JastipScreen(navController) }
        composable("shop") { ShopScreen(navController) }
        composable("ride") { RideScreen(navController) }
        composable("car") { CarScreen(navController) }
        composable("kirim_barang") { KirimBarangScreen(navController) }
        composable("aplikasi_premium") { AplikasiPremiumScreen(navController) }
        composable("cleaning") { CleaningScreen(navController) }
        composable("pindahin") { PindahinScreen(navController) }
        composable("detail_jastip/{id}") { backStackEntry ->
            DetailJastipScreen(navController, backStackEntry.arguments?.getString("id"))
        }
        composable("chat_dummy/{id}") { backStackEntry ->
            ChatDummyScreen(navController, backStackEntry.arguments?.getString("id"))
        }
        composable("riwayat") { RiwayatScreen(navController) }
        composable("profil") { ProfilScreen(navController) }
        composable("seller_profile") { SellerProfileScreen(navController) }
    }
}