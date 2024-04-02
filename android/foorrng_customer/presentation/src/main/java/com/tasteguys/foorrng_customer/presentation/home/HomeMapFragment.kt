package com.tasteguys.foorrng_customer.presentation.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdd.presentation.base.PermissionHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentHomeMapBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.profile.adapter.TruckAdapter
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import com.tasteguys.foorrng_customer.presentation.truck.info.TruckInfoFragment
import com.tasteguys.foorrng_customer.presentation.truck.regist.RegisterTruckFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max
import kotlin.math.min

private const val TAG = "HomeMapFragment"

@AndroidEntryPoint
class HomeMapFragment : MainBaseFragment<FragmentHomeMapBinding>(
    { FragmentHomeMapBinding.bind(it) }, R.layout.fragment_home_map
) {

    private lateinit var mapView: MapView
    private lateinit var nMap: NaverMap
    private val truckVewModel: TruckViewModel by activityViewModels()
    private val homeMapViewModel: HomeMapViewModel by viewModels()

    private val truckAdapter = TruckAdapter()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()

    }



    private fun initView() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.maxHeight = 1200
        mapView = binding.mvMap
        mapView.getMapAsync {
            nMap = it.apply {
                locationSource = FusedLocationSource(
                    this@HomeMapFragment, 5000
                )
                uiSettings.isLocationButtonEnabled = true
                locationTrackingMode = LocationTrackingMode.Follow

                addOnCameraIdleListener {
                    binding.btnCurrentSearch.visibility = View.VISIBLE
                }

                with(homeMapViewModel){
                    val mList =
                        if (!ownerAuthenticatedToggle) markerList else authenticatedMarkerList
                    for (marker in mList) {
                        marker.map = it
                    }
                }
            }
            registerObserve()
        }



        binding.btnCurrentSearch.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            val bound = nMap.contentBounds
            val nw = bound.northWest
            val se = bound.southEast
            val latMin = min(nw.latitude, se.latitude)
            val latMax = max(nw.latitude, se.latitude)
            val lngMin = min(nw.longitude, se.longitude)
            val lngMax = max(nw.longitude, se.longitude)

            truckVewModel.getTruckList(latMin, lngMin, latMax, lngMax)

            binding.btnCurrentSearch.visibility = View.GONE
        }

        binding.btnReport.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_container, RegisterTruckFragment(true, -1))
                .addToBackStack(null)
                .commit()
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        //                        nMap.setContentPadding(0,0,0,1200, true)
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        nMap.setContentPadding(0, 0, 0, 0, true)
                    }

                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                nMap.setContentPadding(
                    0,
                    0,
                    0,
                    (binding.bottomSheet.height * slideOffset).toInt(),
                    true
                )
            }
        }
        )

        binding.btnToggleVerified.setOnClickListener {
            with(homeMapViewModel) {
                toggleOwnerAuthenticate()
                for (marker in markerList) {
                    marker.map = null
                }
                val mList = if (!ownerAuthenticatedToggle) markerList else authenticatedMarkerList
                for (marker in mList) {
                    marker.map = nMap
                }
                val lst = truckList.value!!
                truckAdapter.submitList(if (!ownerAuthenticatedToggle) lst else lst.filter { it.type == "Foodtruck" })
            }
        }
    }

    private fun initAdapter() {
        binding.rvTrucks.apply {
            adapter = truckAdapter.apply {
                setOnItemClickListener(object : BaseHolder.ItemClickListener {
                    override fun onClick(position: Int) {
                        val curTruck = currentList[position]
                        parentFragmentManager.beginTransaction()
                            .replace(
                                R.id.fcv_container,
                                TruckInfoFragment(curTruck.truckId, curTruck.name)
                            )
                            .addToBackStack(null)
                            .commit()
                    }
                })
                setOnButtonClickListener(object : TruckAdapter.TruckListHolder.ButtonClickListener {
                    override fun onToggleClick(position: Int) {
                        val curTruck = currentList[position]
                        truckVewModel.markFavoriteTruck(curTruck.truckId)
                    }

                    override fun onButtonClick(position: Int) {
                    }

                })
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
            }
        }
    }


    private fun registerObserve() {
        truckVewModel.truckListResult.observe(viewLifecycleOwner) { res ->
            if (res.isSuccess) {
                with(homeMapViewModel) {
                    clearMarkerList()
                    val data = res.getOrNull()!!
                    for (truck in data) {
                        val marker = Marker(LatLng(truck.lat, truck.lng)).apply {
                            setOnClickListener {
                                truckAdapter.submitList(listOf(truck))
                                true
                            }
                        }
                        markerList.add(marker)
                        if (truck.type == "Foodtruck") {
                            authenticatedMarkerList.add(marker)
                        }
                    }
                    val mList =
                        if (!ownerAuthenticatedToggle) markerList else authenticatedMarkerList
                    for (marker in mList) {
                        marker.map = nMap
                    }
                    setTruckList(data)
                }
            }
        }

        with(homeMapViewModel){
            truckList.observe(viewLifecycleOwner){ lst->
                truckAdapter.submitList(if(!ownerAuthenticatedToggle) lst else lst.filter { it.type == "Foodtruck" })
            }
        }

    }

}