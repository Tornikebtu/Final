package com.example.e_vaccine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var ChangePasswordEditText : TextView
    private lateinit var changePasswordButton : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        ChangePasswordEditText = findViewById(R.id.ChangePasswordEditText)
        changePasswordButton = findViewById(R.id.changePasswordButton)

        init()
        listener()


    }

    private fun listener() {
        changePasswordButton.setOnClickListener {
            val password = ChangePasswordEditText.text.toString()

            if (password.isEmpty() || password.length < 8){
                Toast.makeText(this,"შეცდომა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().currentUser?.updatePassword(password)
                ?.addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "პაროლი წარმატებით შეიცვალა", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "სცადეთ თავიდან", Toast.LENGTH_SHORT).show()
                    }

            }


        }
    }

    private fun init(){
        ChangePasswordEditText = findViewById(R.id.ChangePasswordEditText)
        changePasswordButton = findViewById(R.id.changePasswordButton)
    }
}