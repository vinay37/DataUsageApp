package com.demo.datausageapp.view.ui.home

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.demo.datausageapp.model.YearlyRecord

class YearlyRecordViewModel(private val record: YearlyRecord) : BaseObservable() {
    @Bindable
    fun getYear(): String = record.year.toString()

    @Bindable
    fun getTotalVolume(): String = record.totalVolume.toString()

    @Bindable
    fun getInfoVisibility(): Int = if (record.isDecreasedYear) View.VISIBLE else View.VISIBLE

}