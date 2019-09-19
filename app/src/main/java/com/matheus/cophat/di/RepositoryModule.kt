package com.matheus.cophat.di

import com.matheus.cophat.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    single {
        IntroRepository(get())
    }

    single {
        ChildrenRepository(get(), get())
    }

    single {
        ParentsRepository(get(), get())
    }

    single {
        QuestionnairesRepository(get(), get())
    }

    single {
        ConfigureRepository(get())
    }

    single {
        GenerateCodeRepository(get(), get())
    }
}