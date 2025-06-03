package com.example.tubmager.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tubmager.R
import androidx.compose.ui.Alignment

data class RideService(
    val id: Int,
    val name: String,
    val price: String,
    val image: Int,
    val vehicleType: String,
    val provider: String,
    val rating: Float
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideScreen(navController: NavController) {
    val categories = listOf(
        Category("Semua", isSelected = true),
        Category("Ojek"),
        Category("Antar Barang"),
        Category("Antar Makanan"),
        Category("Perjalanan Pendek"),
        Category("Perjalanan Jauh")
    )

    val services = listOf(
        RideService(
            id = 1,
            name = "Ojek ke Kampus",
            price = "Rp 15.000",
            image = R.drawable.ub,
            vehicleType = "Motor - Matic",
            provider = "Driver UB 1",
            rating = 4.9f
        ),
        RideService(
            id = 2,
            name = "Antar Paket Kecil",
            price = "Rp 20.000",
            image = R.drawable.paket,
            vehicleType = "Motor - Sport",
            provider = "Driver UB 2",
            rating = 4.7f
        ),
        RideService(
            id = 3,
            name = "Antar Makanan",
            price = "Rp 18.000",
            image = R.drawable.makanan,
            vehicleType = "Motor - Matic",
            provider = "Driver UB 3",
            rating = 4.8f
        ),
        RideService(
            id = 4,
            name = "Perjalanan ke Stasiun",
            price = "Rp 25.000",
            image = R.drawable.stasiun,
            vehicleType = "Motor - Matic",
            provider = "Driver UB 4",
            rating = 4.6f
        ),
        RideService(
            id = 5,
            name = "Ojek Malam",
            price = "Rp 22.000",
            image = R.drawable.malam,
            vehicleType = "Motor - Sport",
            provider = "Driver UB 5",
            rating = 4.5f
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ride") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Open history */ }) {
                        Icon(Icons.Default.History, contentDescription = "History")
                    }
                    IconButton(onClick = { /* Open favorites */ }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D87C0),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchAndFilter("Cari layanan ride...")
            CategoryList(categories = categories)
            RideList(services = services)
        }
    }
}

@Composable
fun RideList(services: List<RideService>) {
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
                text = "Layanan Ride Terbaru",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            TextButton(onClick = { /* View all */ }) {
                Text(
                    text = "Lihat Semua",
                    color = Color(0xFF0D87C0),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(services.size) { index ->
                RideServiceItem(service = services[index])
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun RideServiceItem(service: RideService) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { /* Navigate to service detail */ },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = service.image),
                contentDescription = service.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Text(
                    text = service.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 2
                )

                Text(
                    text = service.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF0D87C0),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    text = service.vehicleType,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = service.provider,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = service.rating.toString(),
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}