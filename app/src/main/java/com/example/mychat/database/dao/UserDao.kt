package com.example.mychat.database.dao

import com.example.mychat.database.Database
import com.example.mychat.database.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot

class UserDao {
    companion object{

        fun addUser(user: User, onCompleteListener:OnCompleteListener<Void>){
            Database.getUsersCollection()
                .document(user.id?:"")
                .set(user)
                .addOnCompleteListener(onCompleteListener)

        }

        fun getUserData(userId:String, onCompleteListener:OnCompleteListener<DocumentSnapshot>){
            Database.getUsersCollection()
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener)
        }

    }
}