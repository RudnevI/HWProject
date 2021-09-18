package com.example.hwproject.lesson_8

import com.example.hwproject.rx.model.Comments
import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST

interface CoroutineApiService {

    @GET("/public/v1/users")
    suspend fun getUsers(): Users

    @POST("/public/v1/users")
    suspend fun createUser(user: User):User

    @GET("/public/v1/comments")
    suspend fun getComments(): Comments


}