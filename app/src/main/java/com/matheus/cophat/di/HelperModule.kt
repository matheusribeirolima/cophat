package com.matheus.cophat.di

import com.matheus.cophat.helper.ResourceManager
import org.koin.dsl.module

val helperModule = module {
    single {
        ResourceManager(get())
    }
}