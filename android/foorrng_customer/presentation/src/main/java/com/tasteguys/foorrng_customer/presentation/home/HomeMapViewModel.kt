package com.tasteguys.foorrng_customer.presentation.home

import androidx.lifecycle.ViewModel
import com.naver.maps.map.overlay.Marker
import com.tasteguys.foorrng_customer.presentation.model.TruckDataWithAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeMapViewModel @Inject constructor() : ViewModel(){

    private var _ownerAuthenticatedToggle = false
    val ownerAuthenticatedToggle: Boolean
        get() = _ownerAuthenticatedToggle

    fun toggleOwnerAuthenticate(){
        _ownerAuthenticatedToggle = !_ownerAuthenticatedToggle
    }

    private val _markerList = mutableListOf<Marker>()

    val markerList: MutableList<Marker>
        get() = _markerList

    private val _authenticatedMarkerList = mutableListOf<Marker>()
    val authenticatedMarkerList: MutableList<Marker>
        get() = _authenticatedMarkerList

    private val _truckList = mutableListOf<TruckDataWithAddress>()
    val truckList:MutableList<TruckDataWithAddress>
        get() = _truckList

    fun clearMarkerList(){
        for (marker in _markerList) {
            marker.map = null
        }
        for (marker in _authenticatedMarkerList) {
            marker.map = null
        }
        _markerList.clear()
        _authenticatedMarkerList.clear()
        _truckList.clear()
    }

//    fun getMarkerList(): MutableList<Marker>{
//        return if(_ownerAuthenticatedToggle)
//            _authenticatedMarkerList
//        else _markerList
//    }
}