package com.example.mychat.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.base.BaseActivity
import com.example.mychat.R
import com.example.mychat.databinding.ActivityLoginBinding
import com.example.mychat.ui.home.HomeActivity
import com.example.mychat.ui.register.RegisterActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(), LoginNavigator {

    var firebaseUser = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun inilazeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (firebaseUser!=null){
            goToHome()
        }
    }
    override fun goToHome() {
        startActivity(Intent(this@LoginActivity , HomeActivity::class.java))
        finish()
    }
}