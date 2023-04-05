package com.example.interviewdemo

import android.content.Context
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.interviewdemo.databinding.ActivityMapsBinding
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    val TAG = "MapsActivityLogs"
    private lateinit var googleMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        val geocoder = Geocoder(this@MapsActivity)
        val getPinCode = intent.getStringExtra("PinCode")

        try {
            val addressList = geocoder.getFromLocationName(getPinCode!!, 1)
            if (addressList?.isNotEmpty()!!) {
                val latitude = addressList[0].latitude
                val longitude = addressList[0].longitude

                val latLng = LatLng(latitude, longitude)
                googleMap.addMarker(MarkerOptions().position(latLng).title("YOUR_MARKER_TITLE"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        } catch (e: IOException) {
            Log.d(TAG, "onMapReady: ${e.message}")
        }
        // Add a marker in Sydney and move the camera

    }
}