package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentFoodtruckReviewTabBinding
import com.tasteguys.foorrng_owner.presentation.model.FoodTruckInfo


class FoodtruckReviewTabFragment(
    private val foodTruckInfo: FoodTruckInfo
) : BaseFragment<FragmentFoodtruckReviewTabBinding>(
    FragmentFoodtruckReviewTabBinding::bind, R.layout.fragment_foodtruck_review_tab
) {

}