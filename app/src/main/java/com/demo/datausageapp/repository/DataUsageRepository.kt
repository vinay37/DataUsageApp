package com.demo.datausageapp.repository

import androidx.lifecycle.LiveData
import com.demo.datausageapp.api.ApiResponse
import com.demo.datausageapp.api.DataUsageApi
import com.demo.datausageapp.api.Resource
import com.demo.datausageapp.model.DataUsageResponse
import com.demo.datausageapp.model.Quarter
import com.demo.datausageapp.model.Record
import com.demo.datausageapp.model.YearlyRecord
import com.demo.datausageapp.room.YearlyRecordDao
import org.koin.dsl.module

val repoModule = module {
    factory { DataUsageRepository(get(), get()) }
}

class DataUsageRepository(val yearlyRecordDao: YearlyRecordDao, private val dataUsageApi: DataUsageApi) {

     fun getMobileDataUsage(resourceId: String): LiveData<Resource<List<YearlyRecord>>> {

        return object : NetworkBoundResource<List<YearlyRecord>, DataUsageResponse>() {

            override fun saveFetchData(item: DataUsageResponse) {
                val recordList = item.result.records
                recordList.let { list ->
                    yearlyRecordDao.insertAllRecords(manipulateDataUsageInfo(list))
                }
            }

            override fun shouldFetch(data: List<YearlyRecord>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<YearlyRecord>> {
                return yearlyRecordDao.getAllRecords()
            }

            override fun fetchService(): LiveData<ApiResponse<DataUsageResponse>> {
                return dataUsageApi.getMobileDataUsage(resourceId)
            }
        }.asLiveData

    }

    fun manipulateDataUsageInfo(recordList: ArrayList<Record>): ArrayList<YearlyRecord> {
        var quarterList: ArrayList<Quarter?> = arrayListOf()
        val yearlyRecordListWrapper: ArrayList<YearlyRecord> = arrayListOf()

        val yearStartIndex = 2004
        val yearEndIndex = 2019
        var totalVolume = 0.0f
        var previousUsage = 0.0f
        var isDecreasedYear = false

        recordList.forEach {
            val splitArray = it.quarter.split("-")
            val year: Int = splitArray[0].toInt()
            val quarterName: String = splitArray[1]

            if (year in yearStartIndex..yearEndIndex) {
                quarterList.add(
                    Quarter(it.id, year, quarterName.replace("Q", "Quarter 0"), it.volume,
                        previousUsage > it.volume)
                )
                if(previousUsage > it.volume) {
                    isDecreasedYear = true
                }
                previousUsage = it.volume
                totalVolume += it.volume
                if ("Q4" == quarterName) {
                    yearlyRecordListWrapper.add(YearlyRecord(year, totalVolume, isDecreasedYear, quarterList))
                    quarterList = arrayListOf()
                    previousUsage = 0.0f
                    totalVolume = 0.0f
                    isDecreasedYear = false
                }
            }
        }

        return yearlyRecordListWrapper
    }
}