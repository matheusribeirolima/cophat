package com.example.cophat.di

import com.example.cophat.ui.base.ErrorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { ErrorViewModel() }
}