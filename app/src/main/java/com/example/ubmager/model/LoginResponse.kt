package com.example.ubmager.model

data class LoginResponse(
    val token: String, // Token yang akan digunakan untuk autentikasi
    val message: String? // Pesan sukses atau error dari API
)