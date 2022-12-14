package com.example.sdgbachelorproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.databinding.FragmentFriendsBinding
import com.example.sdgbachelorproject.utils.observeAsLiveData
import com.example.sdgbachelorproject.utils.switchFragment
import com.example.sdgbachelorproject.view.adapters.FriendsAdapter
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_friends.view.*
import javax.inject.Inject

class FriendsFragment : Fragment(), FriendsAdapter.OnFriendRemoveListener {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!
    val usersFriends: MutableList<String> = mutableListOf()
    private lateinit var friendsAdapter: FriendsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = view.friends_recycler_view
        friendsAdapter = FriendsAdapter(usersFriends, this)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = friendsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllUsers()
        setupObservers()
    }

    private fun setupObservers() {
        btn_add_friends.setOnClickListener {
            switchFragment(R.id.friendsAddFragment)
        }

        signInViewModel.currentUserFriends?.observeAsLiveData(viewLifecycleOwner) {
            usersFriends.clear()
            it.forEach {
                usersFriends.add(it.username)
            }
            friendsAdapter.notifyDataSetChanged()
        }
    }

    private fun getAllUsers() {
        signInViewModel.getAllUsers()
        signInViewModel.getUserFriends()
    }

    override fun onFriendRemove(position: Int) {
        val userFriendsNames = usersFriends
        val userFriendsObject = signInViewModel.currentUserFriends.value
        val userIds = mutableListOf<String>()

        userFriendsNames.forEach { name ->
            val uId = userFriendsObject?.find { user ->
                user.username == name
            }
            uId?.userId?.let { userIds.add(it) }
        }

        signInViewModel.removeFriend(userIds)
        friendsAdapter.notifyItemRemoved(position)
    }
}