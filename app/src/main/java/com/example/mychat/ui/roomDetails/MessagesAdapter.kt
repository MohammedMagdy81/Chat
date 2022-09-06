package com.example.mychat.ui.roomDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mychat.Data
import com.example.mychat.R
import com.example.mychat.database.model.Message
import com.example.mychat.databinding.ItemReceiveBinding
import com.example.mychat.databinding.ItemSendBinding

class MessagesAdapter(val messagesList: MutableList<Message>) :
    RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {

    val SEND_MESSAGE_TYPE = 0
    val RECEIVE_MESSAGE_TYPE = 1

    override fun getItemViewType(position: Int): Int {
        val message = messagesList.get(position)
        if (message.senderId.equals(Data.user?.id)) {
            return SEND_MESSAGE_TYPE
        } else {
            return RECEIVE_MESSAGE_TYPE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {

        if (viewType == SEND_MESSAGE_TYPE) {
            val itemSendBinding: ItemSendBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_send, parent, false
            )
            return MessageSendViewHolder(itemSendBinding)
        } else {
            val itemReceiveBinding: ItemReceiveBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_receive, parent, false
            )
            return MessageReceivedViewHolder(itemReceiveBinding)
        }
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val message = messagesList.get(position)
        holder.bind(message)
//        val viewType=getItemViewType(position)
//        if (viewType==SEND_MESSAGE_TYPE){
//            val viewHolder= holder as MessageSendViewHolder
//
//        }else if (viewType==RECEIVE_MESSAGE_TYPE){
//            val viewHolder= holder as MessageReceivedViewHolder
//
//        }
    }

    override fun getItemCount(): Int = messagesList.size

    fun addMessages(addedMessagesList: MutableList<Message>) {
        val oldMessagesCount = messagesList.size
        messagesList.addAll(addedMessagesList)
        notifyItemRangeInserted(oldMessagesCount, addedMessagesList.size)
    }


    abstract class MessagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(message: Message)
    }

    class MessageSendViewHolder(val itemSendBinding: ItemSendBinding) :
        MessagesViewHolder(itemSendBinding.root) {
        override fun bind(message: Message) {
            itemSendBinding.messageModel = message
            itemSendBinding.invalidateAll()
        }


    }

    class MessageReceivedViewHolder(val itemReceivedBinding: ItemReceiveBinding) :
        MessagesViewHolder(itemReceivedBinding.root) {

        override fun bind(message: Message) {
            itemReceivedBinding.messageModel = message
            itemReceivedBinding.invalidateAll()
        }

    }
}