package com.example.ubmager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ubmager.api.ApiClient
import kotlinx.coroutines.launch

// Model data untuk barang
data class Item(
    var id: Int,
    var name: String,
    var price: Int,
    var description: String,
    var sellerPhone: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(
            onNavigateToRegister = { currentScreen = "register" },
            onLoginSuccess = { currentScreen = "main" }
        )
        "register" -> RegisterScreen(onNavigateToLogin = { currentScreen = "login" })
        "main" -> UBMagerApp()
    }
}

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit, onLoginSuccess: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                try {
                    val response = ApiClient.apiService.login(email, password)
                    ApiClient.setToken(response.token) // Simpan token
                    message = "Login berhasil"
                    onLoginSuccess() // Pindah ke halaman utama
                } catch (e: Exception) {
                    message = "Username atau password salah"
                }
            }
        }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToRegister) {
            Text("Belum punya akun? Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(message)
    }
}

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nama") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = passwordConfirmation, onValueChange = { passwordConfirmation = it }, label = { Text("Konfirmasi Password") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                try {
                    val response = ApiClient.apiService.register(name, email, password, passwordConfirmation)
                    message = "Registrasi berhasil: ${response.message}"
                } catch (e: Exception) {
                    message = "Registrasi gagal: ${e.message}"
                }
            }
        }) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToLogin) {
            Text("Sudah punya akun? Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(message)
    }
}

@Composable
fun UBMagerApp() {
    var isSeller by remember { mutableStateOf(true) } // Mode seller/user
    val itemList = remember { mutableStateListOf<Item>() } // Daftar barang
    var itemIdCounter by remember { mutableStateOf(1) } // ID unik untuk barang

    // State untuk input form
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5) // Background mirip e-commerce
    ) {
        Column {
            // Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "UB Mager",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { isSeller = !isSeller },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                        modifier = Modifier
                    ) {
                        Text(
                            text = if (isSeller) "Switch to Buyer" else "Switch to Seller",
                            color = Color.White
                        )
                    }
                }
            }

            // Form input untuk seller
            if (isSeller) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Nama Barang") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = price,
                            onValueChange = { price = it },
                            label = { Text("Harga (Rp)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                        )
                        OutlinedTextField(
                            value = desc,
                            onValueChange = { desc = it },
                            label = { Text("Deskripsi") },
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                        )
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("Nomor WhatsApp") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                        )
                        Button(
                            onClick = {
                                if (name.isNotEmpty() && price.isNotEmpty() && desc.isNotEmpty() && phone.isNotEmpty()) {
                                    val priceInt = price.toIntOrNull() ?: 0
                                    if (priceInt > 0) {
                                        itemList.add(Item(itemIdCounter++, name, priceInt, desc, phone))
                                        name = ""
                                        price = ""
                                        desc = ""
                                        phone = ""
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Text("Tambah Barang", color = Color.White)
                        }
                    }
                }
            }

            // Daftar barang
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(itemList) { item ->
                    ItemCard(
                        item = item,
                        isSeller = isSeller,
                        onUpdate = { updatedItem ->
                            val index = itemList.indexOf(item)
                            if (index != -1) {
                                itemList[index] = updatedItem.copy(id = item.id)
                            }
                        },
                        onDelete = { itemList.remove(item) }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    item: Item,
    isSeller: Boolean,
    onUpdate: (Item) -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current // Mendapatkan konteks untuk Intent

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )
            Text(
                text = "Rp ${item.price}",
                fontSize = 16.sp,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = Color(0xFF757575),
                modifier = Modifier.padding(top = 4.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isSeller) {
                    Button(
                        onClick = { onUpdate(item) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Update", color = Color.White)
                    }
                    Button(
                        onClick = onDelete,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                } else {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("https://wa.me/${item.sellerPhone}?text=Halo, saya tertarik dengan ${item.name}")
                            }
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Chat", color = Color.White)
                    }
                }
            }
        }
    }
}