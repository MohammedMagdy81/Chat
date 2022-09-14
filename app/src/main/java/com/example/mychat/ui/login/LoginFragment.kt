package com.example.mychat.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.databinding.ActivityLoginBinding

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