package com.matheus.cophat.di

import com.matheus.cophat.data.repository.ChildrenRepository
import com.matheus.cophat.data.repository.IntroRepository
import com.matheus.cophat.data.repository.ParentsRepository
import com.matheus.cophat.data.repository.QuestionnairesRepository
import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.feature.parents.viewmodel.ParentsViewModel
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.ui.base.ErrorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { ErrorViewModel() }

    single {
        IntroRepository(get(), get())
    }
    viewModel { IntroViewModel(get(), get()) }

    single {
        ChildrenRepository(get(), get())
    }
    viewModel { ChildrenViewModel(get(), get()) }

    single {
        ParentsRepository(get(), get())
    }
    viewModel { ParentsViewModel(get(), get()) }

    single {
        QuestionnairesRepository(get(), get())
    }
    viewModel { QuestionnairesViewModel(get(), get()) }
}