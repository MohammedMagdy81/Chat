package com.example.mychat.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mychat.R
import com.example.mychat.database.model.Room
import com.example.mychat.databinding.ItemRoomBinding

class RoomsRecyclerAdapter(var roomsList:List<Room>):RecyclerView.Adapter<RoomsRecyclerAdapter.RoomsViewHolder>() {

   class RoomsViewHolder(val itemBinding:ItemRoomBinding): RecyclerView.ViewHolder(itemBinding.root){
       fun bind(room: Room) {
           itemBinding.room=room
       }

   }

    fun changeData(roomsList:List<Room>){
        this.roomsList=roomsList
        notifyDataSetChanged()
    }

    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position:Int,room:Room)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
       val itemBinding:ItemRoomBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.item_room,parent,false)
        return RoomsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val room=roomsList.get(position)
        holder.bind(room)
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,room)
            }
        }
    }

    override fun getItemCount(): Int {
      return roomsList.size
    }
}