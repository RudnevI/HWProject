package com.example.hwproject.lesson_10.model

import com.example.hwproject.lesson_10.repository.PaymentsRepository

class PaymentsModel(private val repository: PaymentsRepository) {

    val payments: List<String>
        get() = repository.loadPayments()

}