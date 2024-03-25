package com.tasteguys.foorrng_owner.presentation.location.manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentLocationManageBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationManageFragment : MainBaseFragment<FragmentLocationManageBinding>(
    FragmentLocationManageBinding::bind, R.layout.fragment_location_manage
) {
    private var map: NaverMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.layout_location_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.layout_location_map, it).commit()
            }
        mapFragment.getMapAsync(mapCallback)
    }

    private val mapCallback = OnMapReadyCallback{ naverMap ->
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