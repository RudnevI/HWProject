package com.example.hwproject.rx.retrofit

import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitClient {

    private val service = Retrofit.Builder()
        .baseUrl("https://gorest.co.in")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build()
        .create(ApiService::class.java)

    fun getUsers(): Observable<Users> = this.service.getUsers()

    fun createUser(user: User):Observable<User> = this.service.createUser(user)

    private val httpClient: OkHttpClient
    get() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                    val newRequest: Request = request.newBuilder()
                        .addHeader("Authorization", "Bearer 35156e8d8775b74103a3d2c23ec87b7ecd9c9c820ade17840010ba400ac536a3")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            .build()
    }


}