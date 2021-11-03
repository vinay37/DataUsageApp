package com.demo.datausageapp.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.datausageapp.api.Resource
import com.demo.datausageapp.model.YearlyRecord
import com.demo.datausageapp.repository.DataUsageRepository
import com.demo.datausageapp.utils.Constants
import com.demo.datausageapp.view.adapter.DataUsageAdapter

class HomeActivityViewModel(private val repository: DataUsageRepository) : ViewModel() {
    lateinit var adapter : DataUsageAdapter

    var recordsLiveData: LiveData<Resource<List<YearlyRecord>>> = MutableLiveData()

    init {
        recordsLiveData = repository.getMobileDataUsage(Constants.RESOURCE_ID)
    }
}
