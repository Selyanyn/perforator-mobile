package com.example.perforatormobile.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
                R.id.navigation_login,
                R.id.navigation_register,
                R.id.navigation_choose_peers -> {
                    bottomNavigationView.visibility = View.GONE
                    bottomNavigationView.setupWithNavController(navController)
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }

        }
    }
}