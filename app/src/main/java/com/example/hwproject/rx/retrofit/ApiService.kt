package com.example.hwproject.rx.retrofit

import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/public/v1/users")
    fun getUsers():Observable<Users>

    @POST("/public/v1/users")
    fun createUser(user: User): Observable<User>
}