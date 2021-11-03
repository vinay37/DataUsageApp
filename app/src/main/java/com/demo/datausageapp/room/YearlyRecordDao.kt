package com.demo.datausageapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.datausageapp.model.YearlyRecord
import com.demo.datausageapp.utils.Constants

@Dao
interface YearlyRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecords(records: ArrayList<YearlyRecord>)

    @Query("SELECT * FROM ${Constants.TABLE_YEARLY_RECORD}")
    fun getAllRecords(): LiveData<List<YearlyRecord>>

    @Query("SELECT * FROM ${Constants.TABLE_YEARLY_RECORD} WHERE ${Constants.RECORD_YEAR}=:year")
    fun getRecordByYear(year: Int): LiveData<YearlyRecord>
}