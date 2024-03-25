package com.tasteguys.foorrng_owner.presentation.location.recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.CircleOverlay
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentLocationRecommendBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.model.RecommendLocation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationRecommendFragment : MainBaseFragment<FragmentLocationRecommendBinding>(
    FragmentLocationRecommendBinding::bind, R.layout.fragment_location_recommend
) {
    private val locationRecommendViewModel: LocationRecommendViewModel by viewModels()

    private var naverMap: NaverMap? = null
    private var recommendCircleList = mutableListOf<CircleOverlay>()

    private var recommendLocationAdapter: RecommendLocationAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.layout_recommend_map) as MapFragment?
                ?: MapFragment.newInstance().also {
                    childFragmentManager.beginTransaction().add(R.id.layout_recommend_map, it)
                        .commit()
                }
        mapFragment.getMapAsync(mapCallback)

    }

    private fun registerObserve() {
        locationRecommendViewModel.recommendLocationList.observe(viewLifecycleOwner) {
            it.onSuccess {
                setAdapter(it)
                setCircleOverlay(it)
            }.onFailure {
                showToast("추천 위치를 불러오는데 실패했습니다.")
            }
        }
    }

    /**
     * 네이버 맵 콜백 이후 호출
     */
    private fun init(){
        bottomSheetSetting()

        registerObserve()
        locationRecommendViewModel.getRecommendLocationList()
    }

    private fun setAdapter(list: List<RecommendLocation>) {
        if (recommendLocationAdapter == null) {
            recommendLocationAdapter = RecommendLocationAdapter(list)
        }

        binding.rvRecommendLocation.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.rvRecommendLocation.adapter = recommendLocationAdapter
    }

    private fun setCircleOverlay(list: List<RecommendLocation>){
        // 기존 CircleOverlay 제거
        recommendCircleList.forEach{
            it.map = null
        }
        // CircleOverlay 추가
        list.forEach {
            val circle = CircleOverlay().apply {
                center = it.latLng
                radius = 800.0
                color = resources.getColor(R.color.recommend_overlay_solid_green, null)
                outlineColor = resources.getColor(R.color.recommend_overlay_line_green, null)
                outlineWidth = 3
                map = naverMap
            }
            recommendCircleList.add(circle)
        }
    }

    private val mapCallback = OnMapReadyCallback { map ->
        init()

        naverMap = map.apply {
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
                title = resources.getString(R.string.location_recommend_title),
                visible = true,
            ).addNavIconClickListener {
                parentFragmentManager.popBackStack()
            }
        )
    }

    private fun bottomSheetSetting() {
        binding.layoutBottomSheet.setOnClickListener { }
        // 바텀시트 제어
        BottomSheetBehavior.from(binding.layoutBottomSheet)
            .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            naverMap?.setContentPadding(0, 0, 0, 0)
                        }

                        BottomSheetBehavior.STATE_EXPANDED -> {
                            naverMap?.setContentPadding(0, 0, 0, binding.layoutBottomSheet.height)
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })

    }
}