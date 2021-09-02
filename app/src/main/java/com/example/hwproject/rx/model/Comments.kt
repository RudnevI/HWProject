package com.example.hwproject.rx.model



data class Comments(val data: ArrayList<Comment>)

data class Comment(
    val id: Int,
    val post_id: Int,
    val name: String,
    val email: String,
    val body: String
)

