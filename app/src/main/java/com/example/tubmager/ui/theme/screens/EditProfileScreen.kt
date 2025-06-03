//package com.example.tubmager.ui.screens
//
//import android.content.Context
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.tubmager.R
//import com.example.tubmager.ui.theme.UBMagerTheme
//import com.example.tubmager.viewmodel.UserViewModel
//import java.io.File
//import java.util.regex.Pattern
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EditProfileScreen(navController: NavController, viewModel: UserViewModel) {
//    var name by remember { mutableStateOf(viewModel.user.value?.name ?: "") }
//    var email by remember { mutableStateOf(viewModel.user.value?.email ?: "") }
//    var phone by remember { mutableStateOf(viewModel.user.value?.phone ?: "") }
//    var address by remember { mutableStateOf(viewModel.user.value?.address ?: "") }
//    var imageFile by remember { mutableStateOf<File?>(null) }
//
//    val context = LocalContext.current
//    val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
//
//    // File picker for image
//    val filePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri ->
//        uri?.let { imageFile = uriToFile(context, it) }
//    }
//
//    UBMagerTheme {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text("Edit Profil", color = Color.White) },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            try {
//                                navController.navigateUp()
//                            } catch (e: Exception) {
//                                viewModel.errorMessage.value = "Navigasi gagal: ${e.message}"
//                            }
//                        }) {
//                            Icon(Icons.Default.ArrowBack, contentDescription = "Kembali", tint = Color.White)
//                        }
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = Color(0xFF0D87C0)
//                    )
//                )
//            }
//        ) { padding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.lorem),
//                    contentDescription = "Foto Profil",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(CircleShape)
//                )
//                Text(
//                    text = "Perbarui Profil Anda",
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF0D87C0)
//                )
//                OutlinedTextField(
//                    value = name,
//                    onValueChange = { name = it.trim() },
//                    label = { Text("Nama") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = Color(0xFF0D87C0),
//                        unfocusedBorderColor = Color(0xFF4FC3F7)
//                    ),
//                    isError = name.isNotEmpty() && name.length < 3
//                )
//                if (name.isNotEmpty() && name.length < 3) {
//                    Text(
//                        text = "Nama minimal 3 karakter",
//                        color = Color.Red,
//                        fontSize = 12.sp
//                    )
//                }
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { email = it.trim() },
//                    label = { Text("Email") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = Color(0xFF0D87C0),
//                        unfocusedBorderColor = Color(0xFF4FC3F7)
//                    ),
//                    isError = email.isNotEmpty() && !emailPattern.matcher(email).matches()
//                )
//                if (email.isNotEmpty() && !emailPattern.matcher(email).matches()) {
//                    Text(
//                        text = "Email tidak valid",
//                        color = Color.Red,
//                        fontSize = 12.sp
//                    )
//                }
//                OutlinedTextField(
//                    value = phone,
//                    onValueChange = { phone = it.trim() },
//                    label = { Text("Nomor Telepon") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = Color(0xFF0D87C0),
//                        unfocusedBorderColor = Color(0xFF4FC3F7)
//                    ),
//                    isError = phone.isNotEmpty() && !phone.matches(Regex("^\\+?[1-9]\\d{9,14}\$"))
//                )
//                if (phone.isNotEmpty() && !phone.matches(Regex("^\\+?[1-9]\\d{9,14}\$"))) {
//                    Text(
//                        text = "Nomor telepon tidak valid (10-15 digit)",
//                        color = Color.Red,
//                        fontSize = 12.sp
//                    )
//                }
//                OutlinedTextField(
//                    value = address,
//                    onValueChange = { address = it.trim() },
//                    label = { Text("Alamat") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = Color(0xFF0D87C0),
//                        unfocusedBorderColor = Color(0xFF4FC3F7)
//                    ),
//                    isError = address.isNotEmpty() && address.length < 5
//                )
//                if (address.isNotEmpty() && address.length < 5) {
//                    Text(
//                        text = "Alamat minimal 5 karakter",
//                        color = Color.Red,
//                        fontSize = 12.sp
//                    )
//                }
//                Button(
//                    onClick = {
//                        try {
//                            filePickerLauncher.launch("image/*")
//                        } catch (e: Exception) {
//                            viewModel.errorMessage.value = "Gagal membuka galeri: ${e.message}"
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(48.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF4FC3F7)
//                    ),
//                    shape = RoundedCornerShape(8.dp),
//                    enabled = !viewModel.isLoading.value
//                ) {
//                    Text(
//                        text = if (imageFile != null) "Gambar Dipilih" else "Pilih Gambar",
//                        color = Color.White,
//                        fontSize = 16.sp
//                    )
//                }
//                Button(
//                    onClick = {
//                        when {
//                            name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() -> {
//                                viewModel.errorMessage.value = "Semua kolom wajib diisi"
//                            }
//                            name.length < 3 -> {
//                                viewModel.errorMessage.value = "Nama minimal 3 karakter"
//                            }
//                            !emailPattern.matcher(email).matches() -> {
//                                viewModel.errorMessage.value = "Email tidak valid"
//                            }
//                            !phone.matches(Regex("^\\+?[1-9]\\d{9,14}\$")) -> {
//                                viewModel.errorMessage.value = "Nomor telepon tidak valid"
//                            }
//                            address.length < 5 -> {
//                                viewModel.errorMessage.value = "Alamat minimal 5 karakter"
//                            }
//                            else -> {
//                                viewModel.updateUser(
//                                    viewModel.userId.value,
//                                    name,
//                                    email,
//                                    phone,
//                                    address,
//                                    imageFile?.absolutePath
//                                ) {
//                                    try {
//                                        navController.navigate("profil") {
//                                            popUpTo("edit_profile") { inclusive = true }
//                                        }
//                                    } catch (e: Exception) {
//                                        viewModel.errorMessage.value = "Navigasi gagal: ${e.message}"
//                                    }
//                                }
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(48.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF0D87C0)
//                    ),
//                    shape = RoundedCornerShape(8.dp),
//                    enabled = !viewModel.isLoading.value
//                ) {
//                    if (viewModel.isLoading.value) {
//                        CircularProgressIndicator(
//                            color = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    } else {
//                        Text("Simpan Perubahan", color = Color.White, fontSize = 16.sp)
//                    }
//                }
//                viewModel.errorMessage.value.takeIf { it.isNotEmpty() }?.let {
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = it,
//                        color = Color.Red,
//                        fontSize = 14.sp,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
//        }
//    }
//}
//
//private fun uriToFile(context: Context, uri: Uri): File? {
//    return try {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        val file = File(context.cacheDir, "profile_image_${System.currentTimeMillis()}.jpg")
//        inputStream?.use { input ->
//            file.outputStream().use { output ->
//                input.copyTo(output)
//            }
//        }
//        file
//    } catch (e: Exception) {
//        null
//    }
//}