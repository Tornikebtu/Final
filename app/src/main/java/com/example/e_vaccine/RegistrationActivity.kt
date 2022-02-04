package com.example.e_vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.e_vaccine.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var registrationUserNameEditText : EditText
    private lateinit var registrationUserLastNameEditText : EditText
    private lateinit var registrationUserDateEditText : EditText
    private lateinit var registrationUserIdEditText : EditText
    private lateinit var registrationUserEmailEditText : EditText
    private lateinit var registrationUserPasswordEditText : EditText
    private lateinit var registrationButton : Button

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("userInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()
        listener()
    }

    private fun listener() {
        registrationButton.setOnClickListener {
            val email = registrationUserEmailEditText.text.toString()
            val password = registrationUserPasswordEditText.text.toString()
            val username = registrationUserNameEditText.text.toString()
            val lastname = registrationUserLastNameEditText.text.toString()
            val date = registrationUserDateEditText.text.toString()
            val userid = registrationUserIdEditText.text.toString()

            val userInfo = User(username, lastname, userid)
//            db.child(auth.currentUser?.uid!!).setValue(userInfo)

            if (email.isEmpty() || password.isEmpty() || password.length < 8 || username.isEmpty()
                || lastname.isEmpty() || date.isEmpty() || userid.isEmpty() || userid.length < 11){
                Toast.makeText(this, "მონაცემები შეავსეთ სრულად.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                } else {
                    Toast.makeText(this, "შეცდომა", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

    private fun init() {
        registrationUserEmailEditText = findViewById(R.id.registrationUserEmailEditText)
        registrationUserNameEditText = findViewById(R.id.registrationUserNameEditText)
        registrationUserLastNameEditText = findViewById(R.id.registrationUserLastNameEditText)
        registrationUserDateEditText = findViewById(R.id.registrationUserDateEditText)
        registrationUserIdEditText = findViewById(R.id.registrationUserIdEditText)
        registrationUserPasswordEditText = findViewById(R.id.registrationUserPasswordEditText)
        registrationButton = findViewById(R.id.registrationButton)
    }
}