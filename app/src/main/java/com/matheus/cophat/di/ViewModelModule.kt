package com.matheus.cophat.di

import com.matheus.cophat.data.repository.IntroRepository
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.ui.base.ErrorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { ErrorViewModel() }

    single {
        IntroRepository(get())
    }
    viewModel { IntroViewModel(get()) }
}