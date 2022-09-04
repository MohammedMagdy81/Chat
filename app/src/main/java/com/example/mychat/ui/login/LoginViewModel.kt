package com.example.mychat.ui.login

import androidx.databinding.ObservableField
import com.example.mychat.base.BaseViewModel
import com.example.mychat.database.DatabaseDao
import com.example.mychat.database.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:BaseViewModel<LoginNavigator>() {
    var email=ObservableField<String>()
    var password=ObservableField<String>()

    var emailError=ObservableField<Boolean>()
    var passwordError=ObservableField<Boolean>()

    val firebaseAuth=Firebase.auth


    fun login(){
        if (!valid()) return
        showloading.value=true
        firebaseAuth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {task->
                showloading.value=false
                if (task.isSuccessful){
                    // retrieve user From FireStore
                    getUserData(firebaseAuth.currentUser!!.uid)
                    // go to Home Page
                   navigator?.goToHome()
                }else{
                    showloading.value=false
                messageLiveData.value=task.exception?.localizedMessage
                }

            }


    }

    private fun getUserData(uid: String) {
        DatabaseDao.getUserData(uid) {task->
            if (task.isSuccessful){
                val user =task.result.toObject(User::class.java)//User retrieve from database

            }else{
                messageLiveData.value=task.exception?.localizedMessage
            }

        }

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