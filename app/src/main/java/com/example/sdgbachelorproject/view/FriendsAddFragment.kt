package com.example.sdgbachelorproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.databinding.FragmentFriendsAddBinding
import com.example.sdgbachelorproject.data.model.User
import com.example.sdgbachelorproject.utils.observeAsLiveData
import com.example.sdgbachelorproject.view.adapters.AddFriendsAdapter
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_friends_add.view.*
import javax.inject.Inject

class FriendsAddFragment : Fragment(), AddFriendsAdapter.OnFriendAddListener {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    private var _binding: FragmentFriendsAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var addFriendsAdapter: AddFriendsAdapter
    val allUsers: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsAddBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = view.add_friends_recycler_view
        addFriendsAdapter = AddFriendsAdapter(allUsers, this)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = addFriendsAdapter
        prepareItems()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun prepareItems() {
        signInViewModel.getAllUsers()
        addFriendsAdapter.notifyDataSetChanged()
    }

    private fun setupObservers() {

        signInViewModel.allUsers?.observeAsLiveData(viewLifecycleOwner) {
            val currentUser = it.find { it.userId == signInViewModel.currentUserId }
            val currentUserFriends = currentUser?.userFriends

            val users = it.filterNot { it == currentUser || currentUserFriends?.contains(it.userId) == true }.distinct()

            allUsers.clear()
            allUsers.addAll(users)
            addFriendsAdapter.notifyDataSetChanged()
        }

    }

    override fun onFriendAdd(user: User) {
        signInViewModel.addFriend(user)
    }

}