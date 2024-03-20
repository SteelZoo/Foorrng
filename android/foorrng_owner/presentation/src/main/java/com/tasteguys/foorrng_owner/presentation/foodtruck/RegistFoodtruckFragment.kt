package com.tasteguys.foorrng_owner.presentation.foodtruck

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentRegistFoodtruckBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistFoodtruckFragment : MainBaseFragment<FragmentRegistFoodtruckBinding>(
    FragmentRegistFoodtruckBinding::bind, R.layout.fragment_regist_foodtruck
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setToolbar() {
        MainToolbarControl(
            visible = true,
            title = resources.getString(R.string.regist_foodtruck_title),
            menuRes = R.menu.menu_check
        ).addMenuItemClickListener {
            showToast("등록 메뉴!")
        }.addNavIconClickListener {
            showToast("등록 뒤로가기")
        }.also {
                mainViewModel.changeToolbar(it)
        }
    }
}