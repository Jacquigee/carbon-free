package com.example.carbonfree.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carbonfree.R
import com.example.carbonfree.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

        private lateinit var binding: ActivitySplashBinding

        private lateinit var appBarConfiguration: AppBarConfiguration
        private lateinit var navController: NavController

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)

            supportActionBar?.hide()


            val navHostFragment = supportFragmentManager.findFragmentById(R.id.splashNav) as NavHostFragment
            val navController = navHostFragment.navController



            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        override fun onSupportNavigateUp(): Boolean {

            val navController = findNavController(R.id.splashNav)

            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }

