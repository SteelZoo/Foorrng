package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentFoodtruckInfoBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.model.FoodTruckInfo
import com.tasteguys.foorrng_owner.presentation.model.Review
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
        binding.vpInfo.adapter = InfoTabPagerAdapter(this,dummyFoodtruck)
        TabLayoutMediator(binding.tlInfoTab,binding.vpInfo){  tab, position ->
            tab.text = if (position == 0) {"정보조회"} else {"리뷰조회"}
        }.attach()
    }

    override fun setToolbar() {
        MainToolbarControl(
            true,
            resources.getString(R.string.info_foodtruck_title),
            menuRes = R.menu.menu_edit
        ).addNavIconClickListener {

        }.addMenuItemClickListener {

        }.also {
            mainViewModel.changeToolbar(it)
        }
    }

    private val dummyFoodtruck = FoodTruckInfo(
        "맛있는 녀석들", "1234123478", "123가1234", "010-1234-1234", "치킨", "공지합니다",
            listOf(
                Review("",10)
            )
        )
}