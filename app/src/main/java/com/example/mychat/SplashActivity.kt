package com.example.mychat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.mychat.ui.home.HomeActivity
import com.example.mychat.ui.start.StartActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(Intent(this,StartActivity::class.java))
                finish()
            },3000)

    }

    override fun onStart() {
        super.onStart()
//        val day= Timestamp.now().toDate().day.toString()
//        val month= Timestamp.now().toDate().month.toString()
//        val year= Timestamp.now().toDate().year.toString()
//        val minute= Timestamp.now().toDate().minutes.toString()
//        val hour= Timestamp.now().toDate().date.toString()
//
//        Log.d("SplashTimeStamp",day+"\n"+month+"\n"+year+"\n"+minute+"\n"+hour)
    }



}