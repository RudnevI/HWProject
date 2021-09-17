package com.example.hwproject.lesson_12.room

import android.content.Context
import androidx.room.Room

object Repository {

    lateinit var database: UserDatabase

    fun initialize(context: Context) {
        database = Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user-database"
        ).build()
    }


}