package com.matheus.cophat.di

import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.feature.generate.viewmodel.GenerateCodeViewModel
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.feature.parents.viewmodel.ParentsViewModel
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.feature.register.viewmodel.RegisterViewModel
import com.matheus.cophat.ui.base.dialog.ErrorViewModel
import com.matheus.cophat.ui.base.view.BottomButtonsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ErrorViewModel(get()) }

    viewModel { BottomButtonsViewModel(get()) }

    viewModel { IntroViewModel(get(), get()) }

    viewModel { ChildrenViewModel(get(), get()) }

    viewModel { ParentsViewModel(get(), get()) }

    viewModel { QuestionnairesViewModel(get(), get()) }

    viewModel { ConfigureViewModel(get(), get()) }

    viewModel { GenerateCodeViewModel(get()) }

    viewModel { RegisterViewModel(get(), get()) }
}