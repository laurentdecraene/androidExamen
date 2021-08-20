package com.ldc.android.travelchecker


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var user :LoggedInUser
    private lateinit var manager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manager = SessionManager(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Serializable Data
                 user = getSerializable("loggedIn") as LoggedInUser


            }
        }
        manager.saveAuthToken(user.token)
        val navView: BottomNavigationView = binding.navView
            val shared = getSharedPreferences("tokens", MODE_PRIVATE)
        val prefsEditor = shared.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefsEditor.putString("loggedIn", json)
        prefsEditor.commit()
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_trips, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}