package com.example.hwproject.lesson_9.state

import com.example.hwproject.lesson_9.ui.login.LoggedInUserView

sealed class LoginState {

    object Loading : LoginState()
    class LoginError(val errorInfo: Int) : LoginState()
    class PasswordError(val errorInfo: Int) : LoginState()
    class SignUpError(val errorInfo: Int): LoginState()
    class Success(val success: LoggedInUserView? = null): LoginState()
    object DataIsValid: LoginState()
    object PasswordIsValid: LoginState()
    object UsernameIsValid: LoginState()


}