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

data class JastipItem(
    val id: Int,
    val name: String,
    val price: String,
    val image: Int,
    val condition: String,
    val provider: String,
    val rating: Float
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JastipScreen(navController: NavController) {
    val categories = listOf(
        Category("Semua", isSelected = true),
        Category("Makanan"),
        Category("Minuman"),
        Category("Sembako"),
        Category("Elektronik"),
        Category("Fashion"),
        Category("Lainnya")
    )

    val items = listOf(
        JastipItem(
            id = 1,
            name = "Nasi Goreng Spesial",
            price = "Rp 25.000",
            image = R.drawable.lorem,
            condition = "Segar - Siap Antar",
            provider = "Warung Makan Sederhana",
            rating = 4.8f
        ),
        JastipItem(
            id = 2,
            name = "Es Teh Manis",
            price = "Rp 8.000",
            image = R.drawable.lorem,
            condition = "Dingin - Kemasan",
            provider = "Kafe Brawijaya",
            rating = 4.7f
        ),
        JastipItem(
            id = 3,
            name = "Minyak Goreng 1L",
            price = "Rp 18.000",
            image = R.drawable.lorem,
            condition = "Baru - Tersegel",
            provider = "Minimarket FEB",
            rating = 4.9f
        ),
        JastipItem(
            id = 4,
            name = "Baju Kaos Polos",
            price = "Rp 50.000",
            image = R.drawable.lorem,
            condition = "Baru - Ready Stock",
            provider = "Toko Fashion UB",
            rating = 4.6f
        ),
        JastipItem(
            id = 5,
            name = "Charger USB-C",
            price = "Rp 75.000",
            image = R.drawable.lorem,
            condition = "Baru - Original",
            provider = "Elektronik Teknik",
            rating = 4.5f
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jastip") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Open cart */ }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
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
            SearchAndFilter("Cari jastip...")
            CategoryList(categories = categories)
            JastipList(items = items, navController = navController)
        }
    }
}

@Composable
fun JastipList(items: List<JastipItem>, navController: NavController) {
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
                text = "Jastip Terbaru",
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
            items(items.size) { index ->
                JastipItem(item = items[index], navController = navController)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun JastipItem(item: JastipItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { navController.navigate("detail_jastip/${item.id}") },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.name,
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
                    text = item.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 2
                )

                Text(
                    text = item.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF0D87C0),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    text = item.condition,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.provider,
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
                        text = item.rating.toString(),
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}