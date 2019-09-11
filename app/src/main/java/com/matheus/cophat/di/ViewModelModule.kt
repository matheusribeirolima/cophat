package com.matheus.cophat.di

import com.matheus.cophat.data.repository.*
import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.feature.parents.viewmodel.ParentsViewModel
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.ui.base.dialog.ErrorViewModel
import com.matheus.cophat.ui.base.view.BottomButtonsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { ErrorViewModel(get()) }

    viewModel { BottomButtonsViewModel(get()) }

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

    single {
        ConfigureRepository(get(), get())
    }
    viewModel { ConfigureViewModel(get(), get()) }
}