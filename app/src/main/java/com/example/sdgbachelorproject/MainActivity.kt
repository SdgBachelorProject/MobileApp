package com.example.sdgbachelorproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.sdgbachelorproject.databinding.ActivityMainBinding
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var signInViewModel: SignInViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance.
        // They did it in the viewmodel
        firebaseAnalytics = Firebase.analytics

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bottom Navigation
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(bottomNavigationView, navController)
    }

    private fun googleAnalyticsEvents() {
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
//            param(FirebaseAnalytics.Param.ITEM_ID, id)
//            param(FirebaseAnalytics.Param.ITEM_NAME, name)
//            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
//        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param("Username", signInViewModel.currentUser.value?.displayName.toString())
            param("Email", signInViewModel.currentUser.value?.email.toString())
        }
    }
}