package com.example.hwproject.rx.model

import java.util.*

data class Users(val data: ArrayList<User>)

data class User(
    var id: Int = 0,
    val name: String,
    val email: String,
    val gender: Gender,
    var status: String? = null,
    var field: String? = null,
    var message: String? = null
)
enum class Gender {
    male,
    female;
}
enum class Status {
    active,
    inactive;
}