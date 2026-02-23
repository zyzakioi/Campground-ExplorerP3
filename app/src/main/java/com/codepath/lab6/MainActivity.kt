package com.codepath.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.lab6.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val parksFragment: Fragment = ParksFragment()
        val campgroundsFragment: Fragment = CampgroundFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener{item ->
            lateinit var fragment: Fragment
            when(item.itemId){
                R.id.nav_parks -> fragment = parksFragment
                R.id.nav_campgrounds -> fragment = campgroundsFragment
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.nav_parks
    }
}
