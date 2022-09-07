package com.example.mychat.ui.start

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.mychat.MainActivity
import com.example.mychat.R
import com.example.mychat.ui.home.HomeActivity
import com.example.mychat.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StartActivity :AppCompatActivity() {
    lateinit var button:Button
    var firebaseUser = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        button= findViewById(R.id.startLetsGoButton)
        button.setOnClickListener {
            startActivity(Intent(this@StartActivity,MainActivity::class.java))
            finish()
        }


    }

    override fun onStart() {
        super.onStart()
        if (firebaseUser != null) {
            goToHome()

        }
    }

    private fun goToHome() {
        startActivity(Intent(this@StartActivity,HomeActivity::class.java))
        finish()
    }
}