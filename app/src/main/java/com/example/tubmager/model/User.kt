package com.example.tubmager.model

import okhttp3.MultipartBody

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val image: String?,
    val rating: Float
)

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val success: Boolean, val message: String, val data: User?)
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    val image: MultipartBody.Part?
)
data class RegisterResponse(val success: Boolean, val message: String, val data: User?)
data class UserResponse(val success: Boolean, val message: String, val data: User)