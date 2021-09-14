package com.example.domain.repository

import com.example.data.User
import dagger.Provides
import dagger.hilt.InstallIn


interface Repository {


    fun getUser(): User


}