package com.app.fitpeo.di

import com.app.fitpeo.ImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ImagesViewModel(get()) }

}