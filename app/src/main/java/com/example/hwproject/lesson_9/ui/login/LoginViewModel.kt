package com.example.hwproject.lesson_9.ui.login

import android.util.Patterns
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hwproject.R
import com.example.hwproject.lesson_9.data.LoginDataSource
import com.example.hwproject.lesson_9.data.LoginRepository
import com.example.hwproject.lesson_9.data.Result
import com.example.hwproject.lesson_9.state.LoginState

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> = _state

    /* private val _loginResult = MutableLiveData<LoginResult>()
     val loginResult: LiveData<LoginResult> = _loginResult*/

    fun login(username: String, password: String) {

        val result = loginRepository.login(username, password)




        if(isUserNameValid(username) && isPasswordValid(password)) {

            _state.value = LoginState.DataIsValid
            if(result is Result.Success) {
                _state.value =
                    LoginState.Success(success = LoggedInUserView(displayName = result.data.displayName))
            }
        }

        else {
            if(!isUserNameValid(username))  {
                _state.value = LoginState.LoginError(R.string.invalid_username)
            }
            else if(!isPasswordValid(password)) {
                _state.value = LoginState.PasswordError(R.string.invalid_password)
            }
            else {
                _state.value = LoginState.SignUpError(R.string.login_failed)
            }
        }


    }


    fun isDataValid(email: String, password: String) : Boolean {
        return if(isUserNameValid(email) && isPasswordValid(password)) {
            _state.value = LoginState.DataIsValid
            true
        } else {
            false
        }
    }

    fun emailCheck(email: String) {
        if (!isUserNameValid(email)) {
            _state.value = LoginState.LoginError(R.string.invalid_username)
        } else {
            _state.value = LoginState.UsernameIsValid
        }
    }

    fun passwordCheck(password: String) {
        if (!isPasswordValid(password)) {
            _state.value = LoginState.PasswordError(R.string.invalid_password)

        } else {
            _state.value = LoginState.PasswordIsValid
        }
    }




    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }



}