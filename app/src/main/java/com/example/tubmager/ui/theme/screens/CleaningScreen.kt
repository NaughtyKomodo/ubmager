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

data class CleaningService(
    val id: Int,
    val name: String,
    val price: String,
    val image: Int,
    val area: String,
    val provider: String,
    val rating: Float
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CleaningScreen(navController: NavController) {
    val categories = listOf(
        Category("Semua", isSelected = true),
        Category("Kamar"),
        Category("Kos"),
        Category("Kantor"),
        Category("Kendaraan"),
        Category("Lainnya")
    )

    val services = listOf(
        CleaningService(
            id = 1,
            name = "Pembersihan Kamar",
            price = "Rp 50.000",
            image = R.drawable.lorem,
            area = "Kamar - 10m²",
            provider = "Clean UB 1",
            rating = 4.8f
        ),
        CleaningService(
            id = 2,
            name = "Pembersihan Kos",
            price = "Rp 150.000",
            image = R.drawable.lorem,
            area = "Kos - 50m²",
            provider = "Clean UB 2",
            rating = 4.7f
        ),
        CleaningService(
            id = 3,
            name = "Pembersihan Kantor",
            price = "Rp 200.000",
            image = R.drawable.lorem,
            area = "Kantor - 100m²",
            provider = "Clean UB 3",
            rating = 4.9f
        ),
        CleaningService(
            id = 4,
            name = "Cuci Motor",
            price = "Rp 30.000",
            image = R.drawable.lorem,
            area = "Kendaraan - Motor",
            provider = "Clean UB 4",
            rating = 4.6f
        ),
        CleaningService(
            id = 5,
            name = "Cuci Mobil",
            price = "Rp 60.000",
            image = R.drawable.lorem,
            area = "Kendaraan - Mobil",
            provider = "Clean UB 5",
            rating = 4.5f
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cleaning") },
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
            SearchAndFilter("Cari layanan cleaning...")
            CategoryList(categories = categories)
            CleaningList(services = services)
        }
    }
}

@Composable
fun CleaningList(services: List<CleaningService>) {
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
                text = "Layanan Cleaning Terbaru",
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
                CleaningServiceItem(service = services[index])
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CleaningServiceItem(service: CleaningService) {
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
                    text = service.area,
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