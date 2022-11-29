package com.example.sdgbachelorproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.databinding.FragmentFriendsBinding
import com.example.sdgbachelorproject.model.repositories.TestFriendsRecycle
import com.example.sdgbachelorproject.view.adapters.FriendsAdapter
import kotlinx.android.synthetic.main.fragment_friends.view.*

class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!
    private val itemsList = ArrayList<TestFriendsRecycle>()
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = view.friends_recycler_view
        friendsAdapter = FriendsAdapter(itemsList)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = friendsAdapter
        prepareItems()

        return view
    }

    private fun prepareItems() {
        itemsList.add(TestFriendsRecycle("Dumitrus Bogus", "3rd"))
        itemsList.add(TestFriendsRecycle("Pawel", "2nd"))
        itemsList.add(TestFriendsRecycle("Wojtek", "4th"))
        itemsList.add(TestFriendsRecycle("Tom", "6th"))
        itemsList.add(TestFriendsRecycle("Bnal", "1st"))
        itemsList.add(TestFriendsRecycle("Iaox", "2nd"))
        itemsList.add(TestFriendsRecycle("Ljks", "4th"))
        itemsList.add(TestFriendsRecycle("Madb", "5th"))
        itemsList.add(TestFriendsRecycle("Bob", "1st"))
        friendsAdapter.notifyDataSetChanged()
    }
}