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

}