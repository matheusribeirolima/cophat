package com.matheus.cophat.di

import com.matheus.cophat.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    single {
        IntroRepository(get())
    }

    single {
        ConfigureRepository(get())
    }

    single {
        GenerateCodeRepository(get(), get())
    }

    single {
        RegisterRepository(get(), get())
    }

    single {
        QuestionsRepository(get(), get())
    }

    single {
        QuestionnairesRepository(get())
    }
}