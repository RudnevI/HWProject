package com.example.hwproject.lesson_8

import android.os.Looper
import com.example.hwproject.rx.model.Comments
import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import com.example.hwproject.rx.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CoroutineRetrofitClient {

    private val service = Retrofit.Builder()
        .baseUrl("https://gorest.co.in")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
        .create(CoroutineApiService::class.java)

    fun getUsers(): Flow<Users> = flow {

        emit(service.getUsers())
        println("#Coroutine: ${Looper.myLooper() == Looper.getMainLooper()}")
    }
        .flowOn(Dispatchers.IO)

    fun createUser(user: User): Flow<User> {
        return flow { emit(service.createUser(user)) }.flowOn(Dispatchers.IO)
    }

    fun getComments(): Flow<Comments> = flow{
        emit(service.getComments())
    }
        .flowOn(Dispatchers.IO)

    private val httpClient: OkHttpClient
    get() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val request: Request = chain.request()
                val newRequest: Request = request.newBuilder()
                    .addHeader("Authorization", "Bearer 35156e8d8775b74103a3d2c23ec87b7ecd9c9c820ade17840010ba400ac536a3")
                    .build()
                chain.proceed(newRequest)
            })
            .build()
    }


}