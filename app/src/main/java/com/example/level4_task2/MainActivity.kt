package com.example.level4_task2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    var toolbar : androidx.appcompat.widget.Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.tbMain))

        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tbMain)




        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_history -> {
                navController.navigate(R.id.action_gameFragment_to_historyFragment)
                toolbar?.title = "Your history"
                toolbar?.setNavigationIcon(R.drawable.abc_vector_test)
                toolbar?.setNavigationOnClickListener(){
                    navController.popBackStack()
                    toolbar?.navigationIcon = null
                    toolbar?.title = "Level4 task2"
                }
                true
            }

            R.id.action_delete_all -> {
                false
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}