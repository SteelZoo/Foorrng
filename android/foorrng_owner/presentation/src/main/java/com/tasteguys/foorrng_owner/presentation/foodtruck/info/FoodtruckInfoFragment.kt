package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentFoodtruckInfoBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl

class FoodtruckInfoFragment : MainBaseFragment<FragmentFoodtruckInfoBinding>(
    FragmentFoodtruckInfoBinding::bind, R.layout.fragment_foodtruck_info
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}