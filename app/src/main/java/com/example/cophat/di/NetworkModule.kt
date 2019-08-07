package com.example.cophat.di

import com.example.cophat.BuildConfig
import com.example.cophat.data.source.remote.CophatApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().level = HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor().level = HttpLoggingInterceptor.Level.NONE
        }
    }
    single {
        OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(get())
            .build()
    }
    single {
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(CophatApi::class.java)
    }
}