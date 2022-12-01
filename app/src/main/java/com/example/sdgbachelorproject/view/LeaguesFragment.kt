package com.example.sdgbachelorproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.databinding.FragmentLeaguesBinding
import com.example.sdgbachelorproject.model.repositories.TestData
import com.example.sdgbachelorproject.view.adapters.LeaguesAdapter
import kotlinx.android.synthetic.main.fragment_leagues.view.*

class LeaguesFragment : Fragment() {

    private var _binding: FragmentLeaguesBinding? = null
    private val binding get() = _binding!!
    private val itemsList = ArrayList<TestData>()
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
        itemsList.add(TestData("Dumitrus Bogus", 32))
        itemsList.add(TestData("Pawel", 645))
        itemsList.add(TestData("Wojtek", 12))
        itemsList.add(TestData("Tom", 923))
        itemsList.add(TestData("Bnal", 54343))
        itemsList.add(TestData("Iaox", 923))
        itemsList.add(TestData("Ljks", 823))
        itemsList.add(TestData("Madb", 1123))
        itemsList.add(TestData("Bob", 38409))
        leaguesAdapter.notifyDataSetChanged()
    }
}