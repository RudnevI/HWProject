package com.example.hwproject.lesson_9.data

import com.example.hwproject.lesson_9.data.model.LoggedInUser
import java.io.IOException
import java.util.*

class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {

        return try {
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: 06.09.2021 : revoke authentication
    }
}