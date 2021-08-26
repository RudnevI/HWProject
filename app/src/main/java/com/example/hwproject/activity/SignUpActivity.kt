package com.example.hwproject.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.hwproject.R

class SignUpActivity : AppCompatActivity() {

    private val PASSWORD_LENGTH = R.integer.password_length
    private val LOGIN_LENGTH = R.integer.login_length
    private val INVALID_INPUT_BACKGROUND_COLOR = R.color.invalid_input_color

    var cities = arrayOf("Almaty", "Astana", "Shymkent", "Aktau", "Karaganda", "Petropavlovsk")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        attachEventListenerToSignInButton()


    }

    private fun isPasswordValid(password: String) : Boolean {
        return password.length >= PASSWORD_LENGTH
    }

    private fun isLoginValid(login : String): Boolean {
        return login.length > LOGIN_LENGTH
    }

    @SuppressLint("ResourceAsColor")
    private fun attachEventListenerToSignInButton() {
        val button = findViewById<AppCompatButton>(R.id.signUpButton);
        val loginField = findViewById<EditText>(R.id.loginText)
        val passwordField = findViewById<EditText>(R.id.passwordText)
        button.setOnClickListener {
            if(isPasswordValid(passwordField.text.toString()) &&
                        isLoginValid(loginField.text.toString())
            )
            {
                // TODO: 20.08.2021 implement adding user to db
            }
            else {
                loginField.setBackgroundColor(INVALID_INPUT_BACKGROUND_COLOR)
                passwordField.setBackgroundColor(INVALID_INPUT_BACKGROUND_COLOR)

            }
        }
    }


}