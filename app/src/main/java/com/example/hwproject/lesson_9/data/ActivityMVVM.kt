package com.example.hwproject.lesson_9.data


import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hwproject.R
import com.example.hwproject.databinding.ActivityMvvmBinding
import com.example.hwproject.lesson_9.state.LoginState
import com.example.hwproject.lesson_9.ui.login.LoggedInUserView
import com.example.hwproject.lesson_9.ui.login.LoginViewModel
import com.example.hwproject.lesson_9.ui.login.LoginViewModelFactory

class ActivityMVVM : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading



        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.state.observe(this, Observer {
            when(it) {
                LoginState.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                    loading.visibility = View.GONE
                }
                is LoginState.LoginError -> {
                    toggleLoginButton(false)
                }
                is LoginState.PasswordError -> {
                    toggleLoginButton(false)
                }
                is LoginState.SignUpError -> {

                }
                is LoginState.DataIsValid -> {
                    toggleLoginButton(true)
                }
            }
        })

        loginViewModel.state.observe(this@ActivityMVVM, Observer {
            val loginState = it?: return@Observer

            login.isEnabled = loginState.isDataValid

            if(loginState.usernameError!=null) {
                userName.error = getString(loginState.usernameError)
            }
            if(loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })
        loginViewModel.loginResult.observe(this@ActivityMVVM, Observer {
            val loginResult = it?: return@Observer
            loading.visibility = View.GONE

            if(loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if(loginResult.success != null) {
                updateViewWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
            finish()

        })

        userName.afterTextChanged {
            loginViewModel.loginDataChanged(
                userName.text.toString(),
                password.text.toString()
            )
        }
        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    userName.text.toString(),
                    userName.text.toString()
                )
            }

            setOnEditorActionListener { v, actionId, event ->
                when(actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            userName.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }
            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(userName.text.toString(), password.text.toString())
            }
        }





    }

    private fun showLoginFailed(@StringRes errorString: Int) {
            Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun updateViewWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun toggleLoginButton(isEnabled: Boolean) {
        login.isEnabled = true
    }
}

fun  EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
    })
}