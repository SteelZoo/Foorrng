package com.tasteguys.foorrng_customer.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _truckList = MutableLiveData<List<TruckDataWithAddress>> ()
    val truckList: LiveData<List<TruckDataWithAddress>>
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
    }

    fun setTruckList(list: List<TruckDataWithAddress>){
        _truckList.postValue(list)
    }
}