package com.example.sdgbachelorproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.model.repositories.TestFriendsRecycle
import io.reactivex.annotations.NonNull

internal class FriendsAdapter(private var itemsList: List<TestFriendsRecycle>) :
    RecyclerView.Adapter<FriendsAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userName: TextView = view.findViewById(R.id.txt_item_friends_username)
        val leagueInfo: TextView = view.findViewById(R.id.txt_item_friends_leagues_info)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.userName.text = item.userName
        holder.leagueInfo.text = "In " + item.leagueInfo + " league"
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}