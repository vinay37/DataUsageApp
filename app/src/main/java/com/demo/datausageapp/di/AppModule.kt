package com.demo.datausageapp.di

import android.app.Application
import androidx.room.Room
import com.demo.datausageapp.room.AppDatabase
import com.demo.datausageapp.room.YearlyRecordDao
import org.koin.dsl.module

val appDatabaseModule = module {
    single { provideDatabase(get()) }
    single { provideYearlyRecordDao(get()) }
}

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "SPHMobileDataUsage.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}

fun provideYearlyRecordDao(database: AppDatabase): YearlyRecordDao {
    return database.yearlyRecordDao()
}