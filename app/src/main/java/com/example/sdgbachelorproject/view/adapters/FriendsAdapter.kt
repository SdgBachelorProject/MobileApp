package com.example.sdgbachelorproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.R
import io.reactivex.annotations.NonNull

internal class FriendsAdapter(
    private var itemsList: MutableList<String>,
    private val listener: OnFriendRemoveListener
) :
    RecyclerView.Adapter<FriendsAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        var userName: TextView = view.findViewById(R.id.txt_item_friends_username)
        val leagueInfo: TextView = view.findViewById(R.id.txt_item_friends_leagues_info)
        val removeButton: ImageView = view.findViewById(R.id.ic_friends_delete)

        init {
            removeButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }
    }

    interface OnFriendRemoveListener {
        fun onFriendRemove(position: Int)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.userName.text = item
        holder.removeButton.setOnClickListener {
            itemsList.removeAt(holder.adapterPosition)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            listener.onFriendRemove(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}