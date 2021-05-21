package com.akih.maps

import android.Manifest
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.akih.maps.databinding.ActivityMainBinding
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMaps: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        askPermission()
        val maps = MapsFragment()

        binding.btnMaps.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, maps)
                commit()
            }
        }
    }

    private fun askPermission(){
        askPermission(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ){

        }.onDeclined{e ->
            if (e.hasDenied()){
                e.denied.forEach{

                }

                AlertDialog.Builder(this)
                    .setMessage("Please Accept Our Permission")
                    .setPositiveButton("Yes"){_,_ ->
                        e.askAgain()
                    }
                    .setNegativeButton("No"){dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }

            if (e.hasForeverDenied()){
                e.foreverDenied.forEach {

                }
                e.goToSettings()
            }
        }
    }
}