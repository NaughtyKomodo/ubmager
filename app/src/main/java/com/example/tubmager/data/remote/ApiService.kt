package com.example.tubmager.data.remote

import com.example.tubmager.model.LoginRequest
import com.example.tubmager.model.LoginResponse
import com.example.tubmager.model.RegisterRequest
import com.example.tubmager.model.RegisterResponse
import com.example.tubmager.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UBMagerApiService {
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("api/user/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponse

    @Multipart
    @PUT("api/user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Part("name") name: String,
        @Part("email") email: String,
        @Part("phone") phone: String,
        @Part("address") address: String,
        @Part image: MultipartBody.Part?
    ): UserResponse
}