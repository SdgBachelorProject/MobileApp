package com.example.sdgbachelorproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sdgbachelorproject.R
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
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance.
        // TODO They did it in the viewmodel
        firebaseAnalytics = Firebase.analytics

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bottom Navigation
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(bottomNavigationView, navController)

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Top Navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        navigation_view.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Drawer menu logout button listner
        // its here since drawer_menu doesn't have its own fragment
        navigation_view.getMenu().findItem(R.id.drawer_nav_logout).setOnMenuItemClickListener { menuItem ->
//            signInViewModel.signOut()
            true
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
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

    override fun onSupportNavigateUp(): Boolean {
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}