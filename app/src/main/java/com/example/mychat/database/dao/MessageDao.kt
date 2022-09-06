package com.example.mychat.database.dao

import com.example.mychat.database.Database
import com.example.mychat.database.model.Message
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference

class MessageDao {
    companion object {

        fun sendMessage(
            message: Message,
            onSuccessListener: OnSuccessListener<Void>,
            onFailureListener: OnFailureListener
        ) {
            val room = Database.getRoomsCollection()
                .document(message.roomId ?: "")
            val messages = room.collection("message")
            val messageDoc = messages.document()

            message.id = messageDoc.id
            messageDoc.set(message)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }

        fun getMessagesRef(roomId: String): CollectionReference {
            return Database.getRoomsCollection().document(roomId)
                .collection("message")
        }
    }
}