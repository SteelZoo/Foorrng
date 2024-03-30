package com.tasteguys.foorrng_owner.presentation.location.regist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.PolygonOverlay
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.GeoManager
import com.tasteguys.foorrng_owner.presentation.base.LocationProviderController
import com.tasteguys.foorrng_owner.presentation.base.WeekDaySelectManager
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentLocationRegistBinding
import com.tasteguys.foorrng_owner.presentation.location.AddRundayDialog
import com.tasteguys.foorrng_owner.presentation.location.manage.RunLocationAdapter
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.model.location.RecommendLocation
import com.tasteguys.foorrng_owner.presentation.model.run.RunDay
import com.tasteguys.foorrng_owner.presentation.model.run.RunLocation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

private const val TAG = "LocationRegistFragment_poorrng"

@AndroidEntryPoint
class LocationRegistFragment(
    private val recommentLocation: RecommendLocation? = null
) : MainBaseFragment<FragmentLocationRegistBinding>(
    FragmentLocationRegistBinding::bind, R.layout.fragment_location_regist
) {
    private val locationRegistViewModel: LocationRegistViewModel by viewModels()

    private lateinit var locationProviderController: LocationProviderController
    private var map: NaverMap? = null

    private lateinit var weekDaySelectManager: WeekDaySelectManager
    private var runLocationAdapter: RegistDayAdapter? = null
    @Inject
    lateinit var geoManager: GeoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.layout_location_map) as MapFragment?
                ?: MapFragment.newInstance().also {
                    childFragmentManager.beginTransaction().add(R.id.layout_location_map, it)
                        .commit()
                }
        mapFragment.getMapAsync(mapCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationProviderController = LocationProviderController(mainActivity, this)
        weekDaySelectManager = WeekDaySelectManager(binding.layoutSelectWeekday, dayClickListener)
    }

    private fun init() {
        registerObserve()
    }

    private fun registerObserve() {
        locationRegistViewModel.runDayList.observe(viewLifecycleOwner) {
            it.map { it.day }.toSet().let { selectDay ->
                weekDaySelectManager.setSelectedDay(selectDay)
            }

            if (runLocationAdapter == null) {
                runLocationAdapter = RegistDayAdapter(deleteBtnClickListener)
            }
            if (binding.rvRuninfoRegist.adapter == null) {
                binding.rvRuninfoRegist.adapter = runLocationAdapter
            }
            runLocationAdapter?.submitList(it)
        }

        locationRegistViewModel.runLocation.observe(viewLifecycleOwner) {
            binding.tvAddress.text = it.address
        }
    }

    private val deleteBtnClickListener: (RunDay) -> Unit = {
        locationRegistViewModel.deleteRunDay(it)
    }

    private val dayClickListener: (DayOfWeek, Boolean) -> Unit = { dayOfWeek, isOn ->
        showAddRunDayDialog(dayOfWeek)
    }

    private fun showAddRunDayDialog(dayOfWeek: DayOfWeek) {
        AddRundayDialog(requireContext(), dayOfWeek)
            .setCancelListener { dialog ->
                dialog.dismiss()
            }
            .setCreateListener { dialog, runDay ->
                locationRegistViewModel.addRunDay(runDay)
                dialog.dismiss()
            }
            .show()
    }

    private val mapCallback = OnMapReadyCallback { naverMap ->
        init()

        map = naverMap.apply {
            // 한반도 영역 제한
            extent = LatLngBounds(LatLng(31.43, 122.37), LatLng(44.35, 132.0))
            uiSettings.apply {
                isTiltGesturesEnabled = false
            }

            addOnCameraIdleListener {
                geoManager.getAddress(
                    cameraPosition.target.latitude,
                    cameraPosition.target.longitude
                ) {
                    locationRegistViewModel.setRunLocation(
                        RunLocation(
                            it.replace("대한민국",""), cameraPosition.target
                        )
                    )
                }
            }
        }


    }

}