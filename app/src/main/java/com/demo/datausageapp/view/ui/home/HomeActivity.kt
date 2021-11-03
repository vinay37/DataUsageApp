package com.demo.datausageapp.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demo.datausageapp.R
import com.demo.datausageapp.api.Resource
import com.demo.datausageapp.api.Status
import com.demo.datausageapp.databinding.ActivityHomeBinding
import com.demo.datausageapp.model.YearlyRecord
import com.demo.datausageapp.utils.CommonUtil
import com.demo.datausageapp.view.adapter.DataUsageAdapter
import com.demo.datausageapp.view.ui.general.BaseAppCompatActivity
import com.demo.datausageapp.view.viewholder.DataUsageViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseAppCompatActivity(), DataUsageViewHolder.Delegate {
    private val viewModel: HomeActivityViewModel by viewModel()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            initView()
            observeViewModel()
        }

        if (!CommonUtil.isNetworkAvailable(this@HomeActivity)) {
            showSnackBar(binding.homeRecordsContainer, getString(R.string.no_internet))
        }
    }

    private fun initView() {
        val linearLayout = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.rvRecords.layoutManager = linearLayout
        viewModel.adapter = DataUsageAdapter(this)
        binding.rvRecords.adapter = viewModel.adapter
    }

    private fun observeViewModel() {
        viewModel.recordsLiveData.observe(this, Observer { it?.let { processResponse(it) } })
    }

    private fun processResponse(response: Resource<List<YearlyRecord>>) {
        when (response.status) {
            Status.LOADING -> {

            }

            Status.SUCCESS -> {
                renderList(response.data as ArrayList<YearlyRecord>)
            }

            Status.ERROR -> {

                showWarningDialog(response.error?.localizedMessage
                    ?: getString(R.string.unable_to_connect_server))
            }
        }
    }

    private fun renderList(records: ArrayList<YearlyRecord>) {
        binding.rvRecords.visibility = View.VISIBLE
        viewModel.adapter.updateList(records)
    }

    override fun onItemClick(yearlyRecord: YearlyRecord, view: View) {
        showBreakdownDialog(this@HomeActivity, yearlyRecord)
    }
}
