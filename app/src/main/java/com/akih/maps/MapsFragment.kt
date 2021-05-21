package com.akih.maps

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.akih.maps.databinding.FragmentMapsBinding
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {
    private var _binding : FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var mMaps: GoogleMap
    var initial_marker    = "Seed nay"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val mFragment = childFragmentManager.findFragmentById(R.id.maps2) as SupportMapFragment
        mFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getLocation()
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMaps = googleMap
        if (ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMaps!!.setMyLocationEnabled(true)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let { currentLocation ->
                val sydney = LatLng(currentLocation.latitude, currentLocation.longitude);
                mMaps.addMarker(MarkerOptions().position(sydney).title(initial_marker));
                mMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}