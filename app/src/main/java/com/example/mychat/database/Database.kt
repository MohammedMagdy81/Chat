package com.example.mychat.database

import android.annotation.SuppressLint
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object Database {
    @SuppressLint("StaticFieldLeak")
    private val database= FirebaseFirestore.getInstance()

    private val USER_COLLECTION="users"
    private val ROOM_COLLECTION="rooms"

    fun getUsersCollection():CollectionReference{
        return database.collection(USER_COLLECTION)
    }
    fun getRoomsCollection():CollectionReference{
        return database.collection(ROOM_COLLECTION)
    }
}