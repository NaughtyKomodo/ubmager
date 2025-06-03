//package com.example.tubmager.viewmodel
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.tubmager.data.remote.RetrofitClient
//import com.example.tubmager.model.LoginRequest
//import com.example.tubmager.model.RegisterRequest
//import com.example.tubmager.model.User
//import com.google.gson.Gson
//import com.google.gson.JsonSyntaxException
//import kotlinx.coroutines.launch
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//import retrofit2.HttpException
//import java.io.File
//import java.io.IOException
//import java.net.ConnectException
//import java.net.SocketTimeoutException
//
//class UserViewModel : ViewModel() {
//    private val _user = mutableStateOf<User?>(null)
//    val user: State<User?> = _user
//
//    private val _userId = mutableStateOf(0)
//    val userId: State<Int> = _userId
//
//    private val _errorMessage = mutableStateOf("")
//    val errorMessage: State<String> = _errorMessage
//
//    private val _isLoading = mutableStateOf(false)
//    val isLoading: State<Boolean> = _isLoading
//
//    fun login(email: String, password: String, onSuccess: () -> Unit) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            _errorMessage.value = ""
//            try {
//                val response = RetrofitClient.apiService.login(LoginRequest(email, password))
//                if (response.success && response.data != null) {
//                    _user.value = response.data
//                    _userId.value = response.data.id
//                    _errorMessage.value = ""
//                    onSuccess()
//                } else {
//                    _errorMessage.value = response.message ?: "Email atau kata sandi salah"
//                }
//            } catch (e: Exception) {
//                handleNetworkException(e, "login")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun register(
//        name: String,
//        email: String,
//        password: String,
//        phone: String,
//        address: String,
//        imagePath: String?,
//        onSuccess: () -> Unit
//    ) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            _errorMessage.value = ""
//            try {
//                val imagePart = imagePath?.let {
//                    val file = File(it)
//                    if (!file.exists()) {
//                        _errorMessage.value = "File gambar tidak ditemukan"
//                        return@launch
//                    }
//                    val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//                    MultipartBody.Part.createFormData("image", file.name, requestBody)
//                }
//                val request = RegisterRequest(name, email, password, phone, address, imagePart)
//                val response = RetrofitClient.apiService.register(request)
//                if (response.success && response.data != null) {
//                    _user.value = null // Require login after registration
//                    _userId.value = 0
//                    _errorMessage.value = ""
//                    onSuccess()
//                } else {
//                    _errorMessage.value = response.message ?: "Registrasi gagal, coba lagi"
//                }
//            } catch (e: Exception) {
//                handleNetworkException(e, "registrasi")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun fetchUser(userId: Int) {
//        viewModelScope.launch {
//            if (userId <= 0) {
//                _errorMessage.value = "ID pengguna tidak valid"
//                return@launch
//            }
//            _isLoading.value = true
//            _errorMessage.value = ""
//            try {
//                val response = RetrofitClient.apiService.getUser(userId)
//                if (response.success) {
//                    _user.value = response.data
//                    _errorMessage.value = ""
//                } else {
//                    _errorMessage.value = response.message ?: "Gagal memuat profil"
//                }
//            } catch (e: Exception) {
//                handleNetworkException(e, "memuat profil")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun updateUser(
//        userId: Int,
//        name: String,
//        email: String,
//        phone: String,
//        address: String,
//        imagePath: String?,
//        onSuccess: () -> Unit
//    ) {
//        viewModelScope.launch {
//            if (userId <= 0) {
//                _errorMessage.value = "ID pengguna tidak valid"
//                return@launch
//            }
//            _isLoading.value = true
//            _errorMessage.value = ""
//            try {
//                val imagePart = imagePath?.let {
//                    val file = File(it)
//                    if (!file.exists()) {
//                        _errorMessage.value = "File gambar tidak ditemukan"
//                        return@launch
//                    }
//                    val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//                    MultipartBody.Part.createFormData("image", file.name, requestBody)
//                }
//                val response = RetrofitClient.apiService.updateUser(
//                    id = userId,
//                    name = name,
//                    email = email,
//                    phone = phone,
//                    address = address,
//                    image = imagePart
//                )
//                if (response.success) {
//                    _user.value = response.data
//                    _errorMessage.value = ""
//                    onSuccess()
//                } else {
//                    _errorMessage.value = response.message ?: "Gagal memperbarui profil"
//                }
//            } catch (e: Exception) {
//                handleNetworkException(e, "memperbarui profil")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun logout() {
//        try {
//            _user.value = null
//            _userId.value = 0
//            _errorMessage.value = ""
//            _isLoading.value = false
//        } catch (e: Exception) {
//            _errorMessage.value = "Gagal logout: ${e.message}"
//        }
//    }
//
//    private fun handleNetworkException(e: Exception, action: String) {
//        _errorMessage.value = when (e) {
//            is HttpException -> {
//                try {
//                    val errorBody = e.response()?.errorBody()?.string()
//                    val errorResponse = Gson().fromJson(errorBody, Map::class.java)
//                    errorResponse["message"]?.toString() ?: "Gagal $action: ${e.message()}"
//                } catch (je: JsonSyntaxException) {
//                    "Gagal $action: Data server tidak valid"
//                }
//            }
//            is ConnectException, is IOException -> "Periksa koneksi internet Anda"
//            is SocketTimeoutException -> "Server tidak merespons, coba lagi nanti"
//            is JsonSyntaxException -> "Data server tidak valid, hubungi dukungan"
//            else -> "Gagal $action: ${e.message ?: "Kesalahan tidak diketahui"}"
//        }
//    }
//}