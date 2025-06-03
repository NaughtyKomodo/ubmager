//package com.example.tubmager.data
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ProductDao {
//    @Insert
//    suspend fun insert(product: Product)
//
//    @Query("SELECT * FROM products WHERE category = :category")
//    fun getProductsByCategory(category: String): Flow<List<Product>>
//}