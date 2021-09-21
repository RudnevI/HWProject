package com.example.hwproject.lesson_10.module

import com.example.hwproject.lesson_10.model.PaymentsModel
import com.example.hwproject.lesson_10.repository.PaymentsRepository
import com.example.hwproject.lesson_10.repository.PaymentsRepositoryImpl
import com.example.hwproject.lesson_10.ui.dashboard.PaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentsModule = module {
    single<PaymentsRepository> { PaymentsRepositoryImpl() }

    factory { PaymentsModel(get()) }

    viewModel {
        PaymentsViewModel(get())
    }
}