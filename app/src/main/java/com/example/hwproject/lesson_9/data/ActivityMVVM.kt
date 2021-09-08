package com.example.hwproject.lesson_9.data


import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hwproject.R
import com.example.hwproject.databinding.ActivityMvvmBinding
import com.example.hwproject.lesson_9.state.LoginState
import com.example.hwproject.lesson_9.state.LoginState.DataIsValid
import com.example.hwproject.lesson_9.ui.login.LoggedInUserView
import com.example.hwproject.lesson_9.ui.login.LoginViewModel
import com.example.hwproject.lesson_9.ui.login.LoginViewModelFactory

class ActivityMVVM : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMvvmBinding

    private lateinit var login: Button
    private lateinit var password: EditText
    private lateinit var userName: EditText
    private lateinit var loading: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = binding.username
        password = binding.password
        login = binding.login
        loading = binding.loading


        login.isEnabled = false;

        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.state.observe(this, Observer {
            when (it) {
                LoginState.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                    loading.visibility = View.GONE
                    updateViewWithUser(LoggedInUserView(userName.text.toString()))
                }
                is LoginState.LoginError -> {
                    toggleLoginButton(false)
                    userName.setBackgroundColor(Color.RED)
                }
                is LoginState.PasswordError -> {
                    toggleLoginButton(false)
                    password.setBackgroundColor(Color.RED)
                }
                is LoginState.SignUpError -> {
                    showLoginFailed(R.string.login_failed)
                }
                is DataIsValid -> {
                    toggleLoginButton(true)
                }
                is LoginState.PasswordIsValid -> {
                    password.setBackgroundColor(Color.GREEN)
                }
                is LoginState.UsernameIsValid -> {
                    userName.setBackgroundColor(Color.GREEN)
                }
            }
        })


        /*loginViewModel.state.observe(this@ActivityMVVM, Observer {
            val loginState = it?: return@Observer

            login.isEnabled = loginState == LoginState.DataIsValid

            if(loginState == LoginState.LoginError) {
                userName.error = getString(loginState.usernameError)
            }
            if(loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })*/
        /*loginViewModel.loginResult.observe(this@ActivityMVVM, Observer {
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

        })*/

        userName.setOnFocusChangeListener { _, _ ->
            loginViewModel.emailCheck(userName.text.toString())
            if(loginViewModel.isDataValid(userName.text.toString(), password.text.toString())) {
                toggleLoginButton(true)
            }

        }
        password.apply {
            setOnFocusChangeListener { _, _ ->
                loginViewModel.passwordCheck(password.text.toString())
                if(loginViewModel.isDataValid(userName.text.toString(), password.text.toString())) {
                    toggleLoginButton(true)
                }
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            userName.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }




        }
        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(userName.text.toString(), password.text.toString())


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
        login.isEnabled = isEnabled
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
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