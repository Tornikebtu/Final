package com.example.e_vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.e_vaccine.models.User
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileUserName : TextView
    private lateinit var profileIdNumber : TextView
    private lateinit var profileQRtext : TextView
    private lateinit var profileChangePasswordButton : Button
    private lateinit var profileLogoutButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()
        listener()

//        db.child(au)
    }

    private fun init() {
        profileUserName = findViewById(R.id.profileUserName)
        profileIdNumber = findViewById(R.id.profileIdNumber)
        profileQRtext = findViewById(R.id.profileQRtext)
        profileChangePasswordButton = findViewById(R.id.profileChangePasswordButton)
        profileLogoutButton = findViewById(R.id.profileLogoutButton)


        profileIdNumber.text = FirebaseAuth.getInstance().currentUser?.uid

    }

    private fun listener() {
        profileLogoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        profileChangePasswordButton.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

    }
}