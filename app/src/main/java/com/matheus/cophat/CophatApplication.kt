package com.matheus.cophat

import android.app.Application
import com.matheus.cophat.di.dataModule
import com.matheus.cophat.di.helperModule
import com.matheus.cophat.di.networkModule
import com.matheus.cophat.di.viewModuleModule
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
            modules(listOf(networkModule, dataModule, helperModule, viewModuleModule))
        }
    }
}