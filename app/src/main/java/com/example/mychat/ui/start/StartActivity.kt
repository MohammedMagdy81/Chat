package com.example.mychat.ui.start

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mychat.R
import com.example.mychat.ui.login.LoginActivity

class StartActivity :AppCompatActivity() {
    lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        button= findViewById(R.id.startLetsGoButton)
        button.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}