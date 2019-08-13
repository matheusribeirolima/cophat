package com.matheus.cophat.di

import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.ui.base.ErrorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { ErrorViewModel() }
    viewModel { IntroViewModel() }
}