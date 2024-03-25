package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.tabs.TabLayoutMediator
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentFoodtruckInfoBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.regist.RegistFoodtruckFragment
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.FoodTruckInfo
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.Review
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.ReviewSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodtruckInfoFragment : MainBaseFragment<FragmentFoodtruckInfoBinding>(
    FragmentFoodtruckInfoBinding::bind, R.layout.fragment_foodtruck_info
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInfoTab()
    }

    private fun setInfoTab() {
        binding.vpInfo.adapter = InfoTabPagerAdapter(this, dummyFoodtruck)
        TabLayoutMediator(binding.tlInfoTab, binding.vpInfo) { tab, position ->
            tab.text = if (position == 0) {
                "정보조회"
            } else {
                "리뷰조회"
            }
            tab.view.background = AppCompatResources.getDrawable(_activity, R.drawable.bg_tab)
        }.attach()
    }

    override fun setToolbar() {
        MainToolbarControl(
            true,
            resources.getString(R.string.info_foodtruck_title),
            menuRes = R.menu.menu_edit
        ).addNavIconClickListener {
            parentFragmentManager.popBackStack()
        }.addMenuItemClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout_main_fragment, RegistFoodtruckFragment())
                .addToBackStack(null)
                .commit()
        }.also {
            mainViewModel.changeToolbar(it)
        }
    }

    private val dummyFoodtruck = FoodTruckInfo(
        "맛있는 녀석들", "1234123478", "123가1234", "010-1234-1234", "치킨", "공지합니다",
        ReviewSet(
            10, listOf(
                Review("음식이 맛있어요", 9),
                Review("특별한 메뉴가 있어요", 0),
                Review("가성비가 좋아요", 5),
                Review("음식이 빨리나와요", 5),
                Review("푸드트럭이 멋져요", 2),
                Review("매장이 깨끗해요", 3),
                Review("사장님이 친절해요", 10),
            )
        )
    )
}