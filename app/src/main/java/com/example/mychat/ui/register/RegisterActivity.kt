package com.example.mychat.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.base.BaseActivity
import com.example.mychat.R
import com.example.mychat.databinding.ActivityRegisterBinding
import com.example.mychat.ui.home.HomeActivity
import com.example.mychat.ui.login.LoginActivity

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() ,RegisterNavigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun inilazeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun goToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}