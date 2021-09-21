package com.example.hwproject.lesson_10.repository

class PaymentsRepositoryImpl : PaymentsRepository {
    override fun loadPayments(): List<String> {
        return listOf("Mobile", "KSK", "Medeo")
    }
}