package com.example.mychat.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychat.base.BaseViewModel
import com.example.mychat.database.DatabaseDao
import com.google.firebase.auth.ktx.auth
import com.example.mychat.database.User
import com.google.android.gms.tasks.OnCompleteListener
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
        val user=User(uid, email.get(),fullName.get())
        DatabaseDao.addUser(user) {it->
            showloading.value=false
            if (it.isSuccessful){
                navigator?.goToHome()

            }else{
                messageLiveData.value= it.exception!!.localizedMessage
            }
        }
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

    fun goToLogin(){
        navigator?.goToLogin()
    }


}