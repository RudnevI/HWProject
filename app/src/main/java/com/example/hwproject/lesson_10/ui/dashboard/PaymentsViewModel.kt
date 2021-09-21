package com.example.hwproject.lesson_10.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.hwproject.lesson_10.repository.PaymentsRepository


class PaymentsViewModel(private val repository: PaymentsRepository) : ViewModel() {
    val payments: List<String>
        get() = repository.loadPayments()

    var id = 0
}