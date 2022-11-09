package com.example.sdgbachelorproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.sdgbachelorproject.databinding.ActivityMainBinding
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var signInViewModel: SignInViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bottom Navigation
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(bottomNavigationView, navController)
    }
}