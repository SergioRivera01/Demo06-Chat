package com.sergiorivera.demo06_chat.ui

import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiorivera.demo06_chat.databinding.FragmentChatBinding
import com.sergiorivera.demo06_chat.databinding.ItemMsgBinding
import com.sergiorivera.demo06_chat.model.Message

class ChatAdapter: ListAdapter<Message, ChatAdapter.ViewHolder>(MessageItemCallBack()) {

    class ViewHolder(val binding: ItemMsgBinding): RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMsgBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = getItem(position)
        holder.binding.tvMsg.text = msg.text
        holder.binding.tvDate.text = msg.date
    }
}

class MessageItemCallBack: DiffUtil.ItemCallback<Message>(){
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.msgId == newItem.msgId
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        if(oldItem.msgId == newItem.msgId){
            return true
        }else{
            return false
        }
    }

}