package com.example.e_vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var editTextTextEmailAddress : EditText
    private lateinit var sendButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        init()
        listeners()


    }

    private fun init(){
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        sendButton = findViewById(R.id.sendButton)
    }

    private fun listeners() {
        sendButton.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "შეცდომა! შეავსეთ აუცილებელი ველი.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "შეამოწმე Email", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "შეცდომა!", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }
}