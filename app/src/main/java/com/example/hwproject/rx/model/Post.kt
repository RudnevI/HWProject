package com.example.hwproject.rx.model

import java.util.*

data class Users(val data: ArrayList<User>)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: Gender,
    val status: String,
    val field: String? = null,
    val message: String? = null
)
enum class Gender {
    male,
    female;
}
enum class Status {
    active,
    inactive;
}