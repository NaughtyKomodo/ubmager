package com.example.tubmager.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tubmager.R
import kotlinx.coroutines.launch
import android.util.Log

data class OnboardingItem(
    val image: Int,
    val title: String,
    val description: String,
    val route: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val items = listOf(
        OnboardingItem(
            image = R.drawable.os1,
            title = "Jastip: Pesan Barang dengan Mudah",
            description = "Titip beli makanan, minuman, atau kebutuhan sehari-hari dari toko terdekat dengan cepat dan aman.",
            route = "jastip"
        ),
        OnboardingItem(
            image = R.drawable.os2,
            title = "Shop: Belanja Kebutuhan Kampus",
            description = "Temukan elektronik, buku, atau alat praktikum bekas dan baru dengan harga mahasiswa.",
            route = "shop"
        ),
        OnboardingItem(
            image = R.drawable.os1,
            title = "Ride: Transportasi Motor Praktis",
            description = "Pesan ojek atau antar barang kecil dengan motor untuk perjalanan cepat di sekitar kampus.",
            route = "ride"
        ),
        OnboardingItem(
            image = R.drawable.os2,
            title = "Car: Perjalanan Nyaman dengan Mobil",
            description = "Nikmati layanan taksi, sewa mobil, atau antar barang besar dengan kendaraan yang nyaman.",
            route = "car"
        ),
        OnboardingItem(
            image = R.drawable.os1,
            title = "Kirim Barang: Pengiriman Aman",
            description = "Kirim dokumen, paket kecil, atau kargo besar dengan kurir terpercaya di sekitar kampus.",
            route = "kirim_barang"
        ),
        OnboardingItem(
            image = R.drawable.os2,
            title = "Aplikasi Premium: Fitur Eksklusif",
            description = "Dapatkan akses ke fitur premium, diskon, dan layanan eksklusif dengan berlangganan.",
            route = "aplikasi_premium"
        ),
        OnboardingItem(
            image = R.drawable.os1,
            title = "Cleaning: Kebersihan Terjamin",
            description = "Pesan jasa pembersihan kamar, kos, atau kendaraan dengan hasil maksimal.",
            route = "cleaning"
        ),
        OnboardingItem(
            image = R.drawable.os2,
            title = "Pindahin: Pindahan Tanpa Ribet",
            description = "Pindah kos, rumah, atau angkut barang besar dengan bantuan profesional.",
            route = "pindahin"
        )
    )

    val pagerState = rememberPagerState(pageCount = { items.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            OnboardingPage(item = items[page])
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(items.size) { iteration ->
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == iteration) Color(0xFF0D87C0)
                            else Color.LightGray
                        )
                        .size(8.dp)
                )
            }
        }

        Button(
            onClick = {
                if (pagerState.currentPage < items.size - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    try {
                        Log.d("OnboardingScreen", "Navigating to login")
                        navController.navigate("login") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    } catch (e: Exception) {
                        Log.e("OnboardingScreen", "Navigation to login failed: ${e.message}")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D87C0)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = if (pagerState.currentPage == items.size - 1) "Mulai Sekarang" else "Lanjut",
                fontSize = 16.sp
            )
        }

        if (pagerState.currentPage == items.size - 1) {
            TextButton(
                onClick = {
                    try {
                        Log.d("OnboardingScreen", "Navigating to login (skip)")
                        navController.navigate("login") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    } catch (e: Exception) {
                        Log.e("OnboardingScreen", "Navigation to login (skip) failed: ${e.message}")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Lewati", color = Color.Gray)
            }
        }
    }
}

@Composable
fun OnboardingPage(item: OnboardingItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = item.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF0D87C0),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = item.description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}













// if login trouble
//package com.example.tubmager.ui.theme.screens
//
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.tubmager.R
//import kotlinx.coroutines.launch
//import android.util.Log
//
//data class OnboardingItem(
//    val image: Int,
//    val title: String,
//    val description: String,
//    val route: String
//)
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun OnboardingScreen(navController: NavController) {
//    val items = listOf(
//        OnboardingItem(
//            image = R.drawable.os1,
//            title = "Jastip: Pesan Barang dengan Mudah",
//            description = "Titip beli makanan, minuman, atau kebutuhan sehari-hari dari toko terdekat dengan cepat dan aman.",
//            route = "jastip"
//        ),
//        OnboardingItem(
//            image = R.drawable.os2,
//            title = "Shop: Belanja Kebutuhan Kampus",
//            description = "Temukan elektronik, buku, atau alat praktikum bekas dan baru dengan harga mahasiswa.",
//            route = "shop"
//        ),
//        OnboardingItem(
//            image = R.drawable.os1,
//            title = "Ride: Transportasi Motor Praktis",
//            description = "Pesan ojek atau antar barang kecil dengan motor untuk perjalanan cepat di sekitar kampus.",
//            route = "ride"
//        ),
//        OnboardingItem(
//            image = R.drawable.os2,
//            title = "Car: Perjalanan Nyaman dengan Mobil",
//            description = "Nikmati layanan taksi, sewa mobil, atau antar barang besar dengan kendaraan yang nyaman.",
//            route = "car"
//        ),
//        OnboardingItem(
//            image = R.drawable.os1,
//            title = "Kirim Barang: Pengiriman Aman",
//            description = "Kirim dokumen, paket kecil, atau kargo besar dengan kurir terpercaya di sekitar kampus.",
//            route = "kirim_barang"
//        ),
//        OnboardingItem(
//            image = R.drawable.os2,
//            title = "Aplikasi Premium: Fitur Eksklusif",
//            description = "Dapatkan akses ke fitur premium, diskon, dan layanan eksklusif dengan berlangganan.",
//            route = "aplikasi_premium"
//        ),
//        OnboardingItem(
//            image = R.drawable.os1,
//            title = "Cleaning: Kebersihan Terjamin",
//            description = "Pesan jasa pembersihan kamar, kos, atau kendaraan dengan hasil maksimal.",
//            route = "cleaning"
//        ),
//        OnboardingItem(
//            image = R.drawable.os2,
//            title = "Pindahin: Pindahan Tanpa Ribet",
//            description = "Pindah kos, rumah, atau angkut barang besar dengan bantuan profesional.",
//            route = "pindahin"
//        )
//    )
//
//    val pagerState = rememberPagerState(pageCount = { items.size })
//    val scope = rememberCoroutineScope()
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        HorizontalPager(
//            state = pagerState,
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        ) { page ->
//            OnboardingPage(item = items[page])
//        }
//
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(bottom = 20.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(items.size) { iteration ->
//                Box(
//                    modifier = Modifier
//                        .padding(2.dp)
//                        .clip(CircleShape)
//                        .background(
//                            if (pagerState.currentPage == iteration) Color(0xFF0D87C0)
//                            else Color.LightGray
//                        )
//                        .size(8.dp)
//                )
//            }
//        }
//
//        Button(
//            onClick = {
//                if (pagerState.currentPage < items.size - 1) {
//                    scope.launch {
//                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                    }
//                } else {
//                    try {
//                        Log.d("OnboardingScreen", "Navigating to login")
//                        navController.navigate("login") {
//                            popUpTo("onboarding") { inclusive = true }
//                        }
//                    } catch (e: Exception) {
//                        Log.e("OnboardingScreen", "Navigation to login failed: ${e.message}")
//                    }
//                }
//            },
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D87C0)),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//        ) {
//            Text(
//                text = if (pagerState.currentPage == items.size - 1) "Mulai Sekarang" else "Lanjut",
//                fontSize = 16.sp
//            )
//        }
//
//        if (pagerState.currentPage == items.size - 1) {
//            TextButton(
//                onClick = {
//                    try {
//                        Log.d("OnboardingScreen", "Navigating to login (skip)")
//                        navController.navigate("login") {
//                            popUpTo("onboarding") { inclusive = true }
//                        }
//                    } catch (e: Exception) {
//                        Log.e("OnboardingScreen", "Navigation to login (skip) failed: ${e.message}")
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            ) {
//                Text("Lewati", color = Color.Gray)
//            }
//        }
//    }
//}
//
//@Composable
//fun OnboardingPage(item: OnboardingItem) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(id = item.image),
//            contentDescription = null,
//            modifier = Modifier
//                .size(280.dp)
//                .padding(bottom = 32.dp),
//            contentScale = ContentScale.Fit
//        )
//
//        Text(
//            text = item.title,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            color = Color(0xFF0D87C0),
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        Text(
//            text = item.description,
//            fontSize = 16.sp,
//            textAlign = TextAlign.Center,
//            color = Color.Gray,
//            modifier = Modifier.padding(horizontal = 8.dp)
//        )
//    }
//}