package com.example.domain.datasource.remote

import com.example.data.User

class MockApi : Api {

    override fun getUser(): User = User(1, "John Smith")

}