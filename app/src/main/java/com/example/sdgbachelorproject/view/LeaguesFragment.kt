package com.example.sdgbachelorproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.databinding.FragmentLeaguesBinding
import com.example.sdgbachelorproject.data.model.LeagueItem
import com.example.sdgbachelorproject.view.adapters.LeaguesAdapter
import kotlinx.android.synthetic.main.fragment_leagues.view.*

class LeaguesFragment : Fragment() {

    private var _binding: FragmentLeaguesBinding? = null
    private val binding get() = _binding!!
    private val itemsList = ArrayList<LeagueItem>()
    private lateinit var leaguesAdapter: LeaguesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeaguesBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = view.leagues_recycler_view
        leaguesAdapter = LeaguesAdapter(itemsList)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = leaguesAdapter
        prepareItems()

        return view
    }

    private fun prepareItems() {
        itemsList.add(LeagueItem("Dumitrus Bogus", 32))
        itemsList.add(LeagueItem("Pawel", 645))
        itemsList.add(LeagueItem("Wojtek", 12))
        itemsList.add(LeagueItem("Tom", 923))
        itemsList.add(LeagueItem("Bnal", 54343))
        itemsList.add(LeagueItem("Iaox", 923))
        itemsList.add(LeagueItem("Ljks", 823))
        itemsList.add(LeagueItem("Madb", 1123))
        itemsList.add(LeagueItem("Bob", 38409))
        leaguesAdapter.notifyDataSetChanged()
    }
}