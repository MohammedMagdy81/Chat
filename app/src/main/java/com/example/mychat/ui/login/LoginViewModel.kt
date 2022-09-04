package com.example.mychat.ui.login

import androidx.databinding.ObservableField
import com.example.mychat.base.BaseViewModel

class LoginViewModel:BaseViewModel<LoginNavigator>() {
    var email=ObservableField<String>()
    var password=ObservableField<String>()

    var emailError=ObservableField<Boolean>()
    var passwordError=ObservableField<Boolean>()

    fun login(){
        if (valid()){

        }

        //
    }

    private fun valid(): Boolean {
        var isValid= true
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid=false
        }else{
            emailError.set(false)
        }
        if(password.get().isNullOrBlank()){
            passwordError.set(true)
            isValid=false
        }else{
            passwordError.set(false)
        }
        return isValid
    }

    fun goToRegister(){
        navigator!!.goToRegister()
    }


}