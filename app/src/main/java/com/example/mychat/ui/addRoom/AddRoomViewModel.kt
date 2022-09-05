package com.example.mychat.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.mychat.base.BaseViewModel
import com.example.mychat.database.dao.RoomDao
import com.example.mychat.database.model.Room

class AddRoomViewModel:BaseViewModel<AddRoomNavigator>() {

    var addRoomName= ObservableField<String>()
    var addRoomDesc= ObservableField<String>()
    var roomAdded= MutableLiveData<Boolean>()

    fun addRoom(){
        if (!valid()) return
        // add Room To Chat
        showloading.value=true
        val room=Room(name = addRoomName.get(), desc = addRoomDesc.get())
        insertRoomToDatabase(room)
    }

    private fun insertRoomToDatabase(room: Room) {
        RoomDao.insertRoom(room) {task->
        showloading.value=false
            if (task.isSuccessful){
                messageLiveData.value="Room Added Successfully "
                roomAdded.value=true
            }else{
                messageLiveData.value=task.exception?.localizedMessage
            }
        }
    }

    private fun valid(): Boolean {
        if (addRoomName.get().isNullOrBlank() ||addRoomDesc.get().isNullOrBlank()){
            messageLiveData.value="Room Name Or Room Description Not Completed ! "
            return false
        }
        return true
    }
}