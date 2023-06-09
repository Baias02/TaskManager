package com.example.taskmanager.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.pref.Pref
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)
        bottomNavigate()
    }

    private fun bottomNavigate() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isUserSeen()) {
            navController.navigate(R.id.onBoardingFragment)
        }

        if (FirebaseAuth.getInstance().currentUser == null) {
            navController.navigate(R.id.authFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val fragmentsWithoutBar = listOf(
            R.id.taskFragment,
            R.id.onBoardingFragment,
            R.id.authFragment
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (fragmentsWithoutBar.contains(destination.id)) {
                navView.isVisible = false
                supportActionBar?.hide()
            }else{
                navView.isVisible = true
                supportActionBar?.show()
            }
        }
    }
}