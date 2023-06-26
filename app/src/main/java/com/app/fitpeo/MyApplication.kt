package com.app.fitpeo

import android.app.Application
import com.app.fitpeo.di.connectionModule
import com.app.fitpeo.di.networkModule
import com.app.fitpeo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, connectionModule, viewModelModule))
        }
    }
}