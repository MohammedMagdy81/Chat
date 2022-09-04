package com.example.mychat.ui.register

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class RegisterViewModel :ViewModel(){

    var fullName=ObservableField<String>()
    var email= ObservableField<String>()
    var password= ObservableField<String>()

    var fullNameError=ObservableField<Boolean>()
    var emailError= ObservableField<Boolean>()
    var passwordError= ObservableField<Boolean>()

    fun register(){
        if (!valid()) return

        // register user . . .

    }


    fun valid():Boolean{
        var isValid= true
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid=false
        }else{
            emailError.set(false)
        }
        if (password.get().isNullOrBlank()){
            passwordError.set(true)
            isValid=false
        }else{
            passwordError.set(false)
        }
        if (fullName.get().isNullOrBlank()){
            fullNameError.set(true)
            isValid=false
        }else{
            fullNameError.set(false)
        }
        return isValid
    }



}