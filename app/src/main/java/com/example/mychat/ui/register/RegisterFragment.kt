package com.example.mychat.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.databinding.ActivityRegisterBinding
import com.example.mychat.ui.home.HomeActivity
import com.example.mychat.ui.login.LoginActivity

class RegisterFragment:BaseFragment<RegisterViewModel, ActivityRegisterBinding>() ,RegisterNavigator{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        findNavController().apply {
            navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

    override fun goToLogin() {
        // TODO: go to login
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}