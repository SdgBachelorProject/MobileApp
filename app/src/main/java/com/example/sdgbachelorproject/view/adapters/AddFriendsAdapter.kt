package com.example.sdgbachelorproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.model.data.User

internal class AddFriendsAdapter(
    private var itemsList: MutableList<User>,
    private val listener: OnFriendAddListener
) :
    RecyclerView.Adapter<AddFriendsAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        var userName: TextView = view.findViewById(R.id.txt_item_friends_username)
        val addButton: ImageView = view.findViewById(R.id.ic_user_add)

        init {
            addButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }
    }

    interface OnFriendAddListener {
        fun onFriendAdd(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_friends, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.userName.text = item.username
        holder.addButton.setOnClickListener {
            itemsList.removeAt(holder.adapterPosition)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            listener.onFriendAdd(item)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}