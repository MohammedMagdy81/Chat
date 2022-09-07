package com.example.mychat.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.databinding.ActivityLoginBinding
import com.example.mychat.ui.home.HomeActivity
import com.example.mychat.ui.register.RegisterActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment<LoginViewModel, ActivityLoginBinding>(), LoginNavigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

    }


    override fun goToHome() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)


    }
}