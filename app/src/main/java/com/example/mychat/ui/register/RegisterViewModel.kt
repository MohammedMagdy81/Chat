package com.example.mychat.ui.register

import android.util.Patterns
import androidx.databinding.ObservableField
import com.example.mychat.Data
import com.example.mychat.base.BaseViewModel
import com.example.mychat.database.dao.UserDao
import com.google.firebase.auth.ktx.auth
import com.example.mychat.database.model.User
import com.google.firebase.ktx.Firebase

class RegisterViewModel :BaseViewModel<RegisterNavigator>(){

    var fullName=ObservableField<String>()
    var email= ObservableField<String>()
    var password= ObservableField<String>()

    var fullNameError=ObservableField<Boolean>()
    var emailError= ObservableField<Boolean>()
    var passwordError= ObservableField<Boolean>()

    val firebaseAuth=Firebase.auth

    fun register(){
        if (!valid()) return
        showloading.value=true
        firebaseAuth.createUserWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {task->
                showloading.value=false
                if (task.isSuccessful){
                    addUserToDatabase(firebaseAuth.currentUser?.uid)
                }else{
                    messageLiveData.value= task.exception!!.localizedMessage
                }
            }
            .addOnFailureListener {it->
                messageLiveData.value=it.localizedMessage
            }

    }

    private fun addUserToDatabase(uid: String?) {
        val user= User(uid, email.get(),fullName.get())
        UserDao.addUser(user) { it->
            showloading.value=false
            if (it.isSuccessful){
                Data.user=user
                navigator?.goToHome()

            }else{
                messageLiveData.value= it.exception!!.localizedMessage
            }
        }
    }


    fun valid():Boolean{
        var isValid= true
        if (email.get().isNullOrBlank()||!Patterns.EMAIL_ADDRESS.matcher(email.get() as CharSequence).matches()){
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

    fun goToLogin(){
        navigator?.goToLogin()
    }


}