package com.akih.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.akih.maps.databinding.ActivityMainBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMaps: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val maps = MapsFragment()

//        val mFragment = supportFragmentManager.findFragmentById(R.id.maps2) as SupportMapFragment
//        mFragment.getMapAsync(this)

        binding.btnMaps.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, maps)
                commit()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
//        mMaps = googleMap
    }
}