package com.example.mychat.ui.roomDetails

import androidx.databinding.ObservableField
import com.example.mychat.Data
import com.example.mychat.base.BaseViewModel
import com.example.mychat.database.dao.MessageDao
import com.example.mychat.database.model.Message
import com.google.firebase.Timestamp

class RoomDetailsViewModel : BaseViewModel<RooDetailsNavigator>() {

    val messageField = ObservableField<String>()
    var roomId:String?=null

    fun sendMessage() {
        if (!valid()) return

        val messageObj=Message(messageText = messageField.get(),
        senderName = Data.user?.fullName,
        senderId = Data.user?.id,
        roomId = roomId,
        time = Timestamp.now())

        MessageDao.sendMessage(messageObj,{
                    messageField.set("")
        },{
           messageLiveData.value=it.localizedMessage
        })

    }

    private fun valid(): Boolean {
        if (messageField.get().isNullOrBlank()) {
            return false
        }
        return true

    }
}