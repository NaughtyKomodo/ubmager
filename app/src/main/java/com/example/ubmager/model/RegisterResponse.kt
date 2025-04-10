package com.example.ubmager.model

data class RegisterResponse(
    val message: String?, // Pesan sukses atau error dari API
    val user: User? // Data pengguna, jika ada
)

data class User(
    val name: String,
    val email: String
)