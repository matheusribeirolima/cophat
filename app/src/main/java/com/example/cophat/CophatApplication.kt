package com.example.cophat

import android.app.Application
import com.example.cophat.di.dataModule
import com.example.cophat.di.helperModule
import com.example.cophat.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CophatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CophatApplication)
            androidFileProperties()
            modules(listOf(networkModule, dataModule, helperModule))
        }
    }
}