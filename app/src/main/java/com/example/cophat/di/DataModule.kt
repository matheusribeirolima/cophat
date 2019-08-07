package com.example.cophat.di

import androidx.room.Room
import com.example.cophat.BuildConfig
import com.example.cophat.data.source.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(androidContext(),
            AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
    }
}