package com.example.sdgbachelorproject.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.FragmentHomeBinding
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.quickstart.auth.kotlin.SignInActivity
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        view.btn_log_out.setOnClickListener {
            signOut()
        }

        view.btn_test_repo.setOnClickListener {
            signInViewModel.printToConsole()
        }

        view.txt_current_user.text = signInViewModel.currentUser.value?.displayName.toString()
        view.txt_current_user_token.text =
            signInViewModel.currentUsersFirebaseToken.value?.token.toString()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signOut() {
        this.context?.let {
            AuthUI.getInstance()
                .signOut(it)
                .addOnCompleteListener {
                    val intent = Intent(this.activity, SignInActivity::class.java)
                    startActivity(intent)
                }
        }
    }

//    private fun deleteAccount() {
//        AuthUI.getInstance()
//            .delete(this)
//            .addOnCompleteListener {
//                // ...
//            }
//    }
}