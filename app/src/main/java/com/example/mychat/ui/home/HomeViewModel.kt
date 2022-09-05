package com.example.mychat.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.mychat.base.BaseViewModel
import com.example.mychat.database.dao.RoomDao
import com.example.mychat.database.model.Room


class HomeViewModel : BaseViewModel<HomeNavigator>() {

    val roomsLiveData = MutableLiveData<List<Room>>()

    fun getRoomsList() {
        val roomList = mutableListOf<Room>()
        RoomDao.getRoomsList({ snapShot ->
            for (document in snapShot) {

                val room = document.toObject(Room::class.java)
                roomList.add(room)
            }
            roomsLiveData.value = roomList
        },
            { exceptiom ->
                messageLiveData.value=exceptiom.localizedMessage
            })
    }


    fun goToAddRoom() {
        navigator?.goToAddRoom()
    }
}