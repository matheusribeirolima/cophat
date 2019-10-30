package com.matheus.cophat.di

import com.matheus.cophat.feature.configure.adapter.AdminRecyclerAdapter
import com.matheus.cophat.feature.questionnaires.adapter.QuestionnaireRecyclerAdapter
import com.matheus.cophat.feature.questions.adapter.SubQuestionRecyclerAdapter
import com.matheus.cophat.helper.ResourceManager
import org.koin.dsl.module

val appModule = module {
    single {
        ResourceManager(get())
    }

    factory {
        AdminRecyclerAdapter()
    }

    factory {
        SubQuestionRecyclerAdapter()
    }

    factory {
        QuestionnaireRecyclerAdapter()
    }
}