package com.example.mychat.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.base.BaseActivity
import com.example.mychat.R
import com.example.mychat.databinding.ActivityLoginBinding
import com.example.mychat.ui.register.RegisterActivity

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() ,LoginNavigator{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this
    }



    override fun getLayoutId(): Int {
       return R.layout.activity_login
    }

    override fun inilazeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun goToRegister() {
        startActivity(Intent(this,RegisterActivity::class.java))
        finish()
    }
}