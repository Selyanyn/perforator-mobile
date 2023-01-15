package com.example.perforatormobile.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.perforatormobile.R
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.perforatormobile.app.fragments.authorization.RegisterFragment
import com.example.perforatormobile.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel = viewModels<ActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val navController = findNavController(R.id.fragment_container_view)
        setVisibilityOfBottomNavigationMenu(navController)
    }

    private fun setVisibilityOfBottomNavigationMenu(navController: NavController)
    {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.menu_bar_view)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_self_review,
                R.id.navigation_verify_peers,
                R.id.navigation_peers_review,
                R.id.navigation_results -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    bottomNavigationView.setupWithNavController(navController)
                }
                else -> {
                    bottomNavigationView.visibility = View.GONE
                }
            }

        }
    }
}