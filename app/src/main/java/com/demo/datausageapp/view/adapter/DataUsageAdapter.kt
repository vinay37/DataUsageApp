package com.demo.datausageapp.view.adapter

import android.view.View
import com.demo.datausageapp.R
import com.demo.datausageapp.model.YearlyRecord
import com.demo.datausageapp.view.viewholder.BaseViewHolder
import com.demo.datausageapp.view.viewholder.DataUsageViewHolder
import java.util.*

class DataUsageAdapter(val delegate: DataUsageViewHolder.Delegate) : BaseAdapter() {

    init {
        addItems(ArrayList<YearlyRecord>())
    }

    fun updateList(records : List<YearlyRecord>) {
        addItems(records)
        notifyItemInserted(items.size)
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return DataUsageViewHolder(view, delegate)
    }

    override fun layout(item: Any?): Int {
        return R.layout.row_data_usage
    }
}
