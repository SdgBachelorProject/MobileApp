package com.example.sdgbachelorproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.model.repositories.TestData
import io.reactivex.annotations.NonNull

internal class LeaguesAdapter(private var itemsList: List<TestData>) :
    RecyclerView.Adapter<LeaguesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userName: TextView = view.findViewById(R.id.txt_item_leagues_username)
        val userXp: TextView = view.findViewById(R.id.txt_item_leagues_xp)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leagues, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.userName.text = item.userName
        holder.userXp.text = item.xp.toString() + " XP"
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}