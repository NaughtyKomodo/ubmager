//package com.example.tubmager
//
//import android.app.Application
//import androidx.room.Room
//import com.example.tubmager.data.AppDatabase
//
//class UBMagerApplication : Application() {
//    lateinit var database: AppDatabase
//        private set
//
//    override fun onCreate() {
//        super.onCreate()
//        database = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            "ubmager-database"
//        ).build()
//    }
//}