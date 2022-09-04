package com.example.mychat.database

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot

class DatabaseDao {
    companion object{

        fun addUser(user:User, onCompleteListener:OnCompleteListener<Void>){
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