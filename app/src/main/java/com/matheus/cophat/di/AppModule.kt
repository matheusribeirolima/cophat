package com.matheus.cophat.di

import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.base.ErrorDialog
import com.matheus.cophat.ui.base.LoadingDialog
import org.koin.dsl.module

val appModule = module {
    single {
        ResourceManager(get())
    }
    single {
        LoadingDialog.newInstance()
    }
    single {
        ErrorDialog.newInstance()
    }
}