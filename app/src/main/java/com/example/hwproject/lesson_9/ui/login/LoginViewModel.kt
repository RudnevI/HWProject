package com.example.hwproject.lesson_9.ui.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hwproject.lesson_9.data.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {

        val result = loginRepository.login(username, password)


    }

}