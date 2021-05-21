package com.akih.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.akih.maps.databinding.ActivityMainBinding
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val maps = MapsFragment()

        binding.btnMaps.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, maps)
                commit()
            }
        }
    }
}