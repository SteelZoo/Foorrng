package com.tasteguys.foorrng_owner.presentation.location.manage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentLocationManageBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.model.location.RunLocationInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationManageFragment : MainBaseFragment<FragmentLocationManageBinding>(
    FragmentLocationManageBinding::bind, R.layout.fragment_location_manage
) {
    private val locationManageVIewModel: LocationManageVIewModel by viewModels()

    private var map: NaverMap? = null

    private var runLocationAdapter: RunLocationAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.layout_location_map) as MapFragment?
                ?: MapFragment.newInstance().also {
                    childFragmentManager.beginTransaction().add(R.id.layout_location_map, it)
                        .commit()
                }
        mapFragment.getMapAsync(mapCallback)
    }

    /**
     * 네이버 맵 콜백 이후 호출
     */
    private fun init() {
        registerObserve()
        locationManageVIewModel.getRunLocationInfoList()
    }

    private fun registerObserve() {
        locationManageVIewModel.runLocationInfoListResult.observe(viewLifecycleOwner) {
            it.onSuccess {
                setAdapter(it)
            }.onFailure {
                showToast(it.message ?: "영업 위치를 불러오는데 실패했습니다.")
            }
        }
    }

    private fun setAdapter(runLocationList: List<RunLocationInfo>) {
        if (runLocationAdapter == null) {
            runLocationAdapter = RunLocationAdapter(runLocationList)
        }
        binding.rvLocationInfo.adapter = runLocationAdapter
    }

    private val mapCallback = OnMapReadyCallback { naverMap ->
        init()

        map = naverMap.apply {
            // 한반도 영역 제한
            extent = LatLngBounds(LatLng(31.43, 122.37), LatLng(44.35, 132.0))
            uiSettings.apply {
                isTiltGesturesEnabled = false
            }
        }
    }

    override fun setToolbar() {
        mainViewModel.changeToolbar(
            MainToolbarControl(
                title = resources.getString(R.string.location_manage_title),
                visible = true,
                menuRes = R.menu.menu_add
            ).addNavIconClickListener {
                parentFragmentManager.popBackStack()
            }.addMenuItemClickListener {
                showToast("영업 위치 추가")
            }
        )
    }
}