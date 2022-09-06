package com.example.mychat.ui.roomDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.R
import com.example.mychat.base.BaseActivity
import com.example.mychat.database.dao.MessageDao
import com.example.mychat.database.model.Message
import com.example.mychat.database.model.Room
import com.example.mychat.databinding.ActivityRoomDetailsBinding
import com.google.firebase.firestore.DocumentChange

class RoomDetailsActivity : BaseActivity<RoomDetailsViewModel, ActivityRoomDetailsBinding>() {
    var room: Room? = null
    lateinit var messagesAdapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        room = intent.getParcelableExtra("room")
        viewBinding.roomName.text = room?.name
        viewModel.roomId = room?.id
        setUpAdapter()
        subscribeToRoomMessages(room?.id)

        viewBinding.icBack.setOnClickListener {
            finish()
        }

    }

    private fun subscribeToRoomMessages(roomId: String?) {
        MessageDao.getMessagesRef(roomId ?: "")
            .orderBy("time")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    showDialog(message = e.localizedMessage)
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

    override fun inilazeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }
}