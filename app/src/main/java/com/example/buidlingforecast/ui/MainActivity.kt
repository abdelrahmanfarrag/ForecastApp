package com.example.buidlingforecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.buidlingforecast.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navcController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        navcController = Navigation.findNavController(this, R.id.nav_host_fragment)
        forecase_bottom_navigation.setupWithNavController(navcController)
        NavigationUI.setupActionBarWithNavController(this, navcController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navcController,null)
    }
}
