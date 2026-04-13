package com.app.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.R
import com.app.models.message.MessageModel

class MessageAdapter(private val messages: List<MessageModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ME = 1
    private val VIEW_TYPE_OTHER = 2

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isMe) VIEW_TYPE_ME else VIEW_TYPE_OTHER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = if (viewType == VIEW_TYPE_ME) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_right_item, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_left_side, parent, false)
        }

        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        (holder.itemView as TextView).text = message.text
    }

    override fun getItemCount() = messages.size
}