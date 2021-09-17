package com.example.hwproject.lesson_12.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val firstName: String,
    val secondName: String

)