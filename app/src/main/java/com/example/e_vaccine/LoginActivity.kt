package com.example.e_vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var loginIdEditText : TextView
    private lateinit var loginPasswordEditText : TextView
    private lateinit var loginButton : Button
    private lateinit var loginRegistrationButton : Button
    private lateinit var loginForgotPasswordButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        listeners()
    }

    private fun init() {
        loginIdEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        loginRegistrationButton = findViewById(R.id.loginRegistrationButton)
        loginForgotPasswordButton = findViewById(R.id.loginForgotPasswordButton)
    }

    private fun listeners() {
        loginButton.setOnClickListener {

            val email = loginIdEditText.text.toString()
            val password = loginPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() || password.length < 8){
                Toast.makeText(this, "Email ან პაროლის ველი ცარიელია", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startActivity(Intent(this,ProfileActivity::class.java))

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,ProfileActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "შეცდომა", Toast.LENGTH_SHORT).show()
                    }

            }
        }

        loginRegistrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        loginForgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))

        }

    }
}