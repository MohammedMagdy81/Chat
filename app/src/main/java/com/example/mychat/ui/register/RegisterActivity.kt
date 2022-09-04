package com.example.mychat.ui.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.base.BaseActivity
import com.example.mychat.R
import com.example.mychat.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun inilazeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
}