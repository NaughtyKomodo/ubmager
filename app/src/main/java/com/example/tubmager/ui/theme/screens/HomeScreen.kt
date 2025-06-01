package com.example.tubmager.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tubmager.R

data class Service(val name: String, val icon: Int, val route: String)
data class Restaurant(val name: String, val image: Int, val distance: String, val category: String, val rating: Float)

@Composable
fun HomeScreen(navController: NavController) {
    val services = listOf(
        Service("Jastip", R.drawable.jastip, "jastip"),
        Service("Shop", R.drawable.shop, "shop"),
        Service("Ride", R.drawable.ride, "ride"),
        Service("Car", R.drawable.ride, "car"),
        Service("Kirim Barang", R.drawable.barang, "kirim_barang"),
        Service("Aplikasi Premium", R.drawable.premium, "aplikasi_premium"),
        Service("Cleaning", R.drawable.cleaning, "cleaning"),
        Service("Pindahin", R.drawable.pindahin, "pindahin")
    )

    val restaurants = listOf(
        Restaurant("Kanemakan", R.drawable.lorem, "0.31 km", "Snacks, Bakery, Sweets", 5.0f),
        Restaurant("Ikhana Kitchen", R.drawable.lorem, "1.04 km", "Snacks, Japanese, Savory", 4.9f),
        Restaurant("Warung Makan Sederhana", R.drawable.lorem, "0.8 km", "Indonesian, Rice", 4.5f),
        Restaurant("Cafe Modern", R.drawable.lorem, "1.2 km", "Coffee, Pastry", 4.7f)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF4FC3F7),
                            Color(0xFF29B6F6),
                            Color(0xFF03A9F4)
                        )
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // Top section with search and notification
            TopSection()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    // Services Grid
                    ServicesGrid(services, navController)
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    // Promotion Cards
                    PromotionSection()
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    // Restaurant Recommendations
                    RestaurantSection(restaurants)
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Bottom Navigation
        CommonBottomNavigationBar(navController, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Bar
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        "Cari sesuatu...",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Notification Bell
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF0D87C0),
                    modifier = Modifier.size(24.dp)
                )
                // Red notification dot
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Red, CircleShape)
                        .offset(x = 8.dp, y = (-8).dp)
                )
            }
        }
    }
}

@Composable
fun ServicesGrid(services: List<Service>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // First row (4 services)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 0 until minOf(4, services.size)) {
                ServiceItem(
                    service = services[i],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second row (4 services)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 4 until minOf(8, services.size)) {
                ServiceItem(
                    service = services[i],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ServiceItem(service: Service, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable { navController.navigate(service.route) }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(Color(0xFF4FC3F7), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = service.icon),
                contentDescription = service.name,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = service.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun PromotionSection() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(2) { index ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = if (index == 0) {
                                    listOf(Color(0xFF0D47A1), Color(0xFF1976D2))
                                } else {
                                    listOf(Color(0xFF00695C), Color(0xFF00897B))
                                }
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (index == 0) "DISKON s.d." else "DISKON",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = if (index == 0) "50%" else "30%",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (index == 0)
                                "Nikmati potongan hingga Rp10.000 untuk jasa titip pertamamu!"
                            else
                                "Maksimum potongan ongkir Rp5.000 untuk pengiriman barang pertamamu!",
                            color = Color.White,
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "*S&K Berlaku",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 8.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantSection(restaurants: List<Restaurant>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cocok Buat Kamu!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Lihat Semua",
                fontSize = 14.sp,
                color = Color(0xFF4FC3F7),
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(restaurants.size) { index ->
                RestaurantCard(restaurants[index])
            }
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = restaurant.image),
                    contentDescription = restaurant.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )

                // Jastip badge
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color(0xFF4FC3F7), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "JASTIP",
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = restaurant.distance,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = restaurant.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = restaurant.category,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFFFD700)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = restaurant.rating.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }
    }
}