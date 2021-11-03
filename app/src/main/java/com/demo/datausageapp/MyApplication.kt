package com.demo.datausageapp

import android.app.Application
import android.content.Context
import com.demo.datausageapp.di.appDatabaseModule
import com.demo.datausageapp.di.networkModule
import com.demo.datausageapp.di.viewModelModule
import com.demo.datausageapp.repository.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            //modules(listOf(networkModule, appDatabaseModule, viewModelModule, repoModule))
            koin.loadModules(listOf(networkModule, appDatabaseModule, viewModelModule, repoModule))
            koin.createRootScope()
        }
    }
}