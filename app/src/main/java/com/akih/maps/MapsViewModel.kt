package com.akih.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapsViewModel : ViewModel() {
    private val places = MutableLiveData<List<Place>>(mutableListOf())
}