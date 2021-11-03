package com.demo.datausageapp.di

import com.demo.datausageapp.view.ui.home.HomeActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeActivityViewModel(get()) }
}