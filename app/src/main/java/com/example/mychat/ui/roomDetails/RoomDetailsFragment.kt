package com.example.mychat.ui.roomDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.database.dao.MessageDao
import com.example.mychat.database.model.Message
import com.example.mychat.database.model.Room
import com.example.mychat.databinding.ActivityRoomDetailsBinding
import com.google.firebase.firestore.DocumentChange

class RoomDetailsFragment:BaseFragment<RoomDetailsViewModel, ActivityRoomDetailsBinding>(),RooDetailsNavigator {
    var room: Room? = null
    lateinit var messagesAdapter: MessagesAdapter
    val args : RoomDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm = viewModel
        room =args.room
        viewModel.roomId = room?.id
        setUpAdapter()
        subscribeToRoomMessages(room?.id)


    }

    private fun subscribeToRoomMessages(roomId: String?) {
        MessageDao.getMessagesRef(roomId ?: "")
            .orderBy("time")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    showDialog(message = e.localizedMessage?:"An unknown Error")
                    return@addSnapshotListener
                }

                val addedMessagesList = mutableListOf<Message>()
                for (dc in snapshot!!.documentChanges) {

                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val message = dc.document.toObject(Message::class.java)
                            addedMessagesList.add(message)
                        }

                    }

                }
                messagesAdapter.addMessages(addedMessagesList)
                if (messagesAdapter.messagesList.size > 3) {
                    viewBinding.messagesRecyclerView.smoothScrollToPosition(messagesAdapter.messagesList.size - 1)
                }

            }

    }


    private fun setUpAdapter() {
        messagesAdapter = MessagesAdapter(mutableListOf())
        viewBinding.messagesRecyclerView.apply {
            adapter = messagesAdapter
            setHasFixedSize(true)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }

    override fun initializeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }
}