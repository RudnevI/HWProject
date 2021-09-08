package com.example.hwproject.lesson_10.repository

interface PaymentsRepository {
    fun loadPayments(): List<String>
}