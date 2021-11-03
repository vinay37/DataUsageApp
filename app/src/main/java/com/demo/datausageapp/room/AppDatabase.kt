package com.demo.datausageapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.datausageapp.model.YearlyRecord

@Database(entities = [(YearlyRecord::class)], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun yearlyRecordDao() : YearlyRecordDao
}