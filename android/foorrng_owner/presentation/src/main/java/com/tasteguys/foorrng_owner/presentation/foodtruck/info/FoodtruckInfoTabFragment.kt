package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentFoodtruckInfoTabBinding
import com.tasteguys.foorrng_owner.presentation.model.FoodTruckInfo

class FoodtruckInfoTabFragment(
    private val foodTruckInfo: FoodTruckInfo
) : BaseFragment<FragmentFoodtruckInfoTabBinding>(
    FragmentFoodtruckInfoTabBinding::bind, R.layout.fragment_foodtruck_info_tab
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            tvTruckName.text = foodTruckInfo.name
            tvBusinessNumber.text = foodTruckInfo.businessNumber
            tvCarNumber.text = foodTruckInfo.carNumber
            tvAccountNumber.text = "1234-1234-1234"
            tvCallNumber.text = foodTruckInfo.callNumber
            tvFoodCategory.text = foodTruckInfo.category
            tvNotice.text = foodTruckInfo.notice
        }
    }
}