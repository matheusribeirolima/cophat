package com.example.cophat.di

import com.example.cophat.helper.ResourceManager
import org.koin.dsl.module

val helperModule = module {
    single {
        ResourceManager(get())
    }
}