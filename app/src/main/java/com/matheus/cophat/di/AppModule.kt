package com.matheus.cophat.di

import com.matheus.cophat.feature.configure.adapter.ApplicatorRecyclerAdapter
import com.matheus.cophat.feature.questionnaires.adapter.QuestionnaireRecyclerAdapter
import com.matheus.cophat.feature.questions.adapter.SubQuestionRecyclerAdapter
import com.matheus.cophat.helper.ResourceManager
import org.koin.dsl.module

val appModule = module {
    single {
        ResourceManager(get())
    }

    factory {
        ApplicatorRecyclerAdapter()
    }

    factory {
        SubQuestionRecyclerAdapter()
    }

    factory {
        QuestionnaireRecyclerAdapter()
    }
}