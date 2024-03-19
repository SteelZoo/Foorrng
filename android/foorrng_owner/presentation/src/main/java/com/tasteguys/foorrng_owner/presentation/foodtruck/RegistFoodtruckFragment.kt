package com.tasteguys.foorrng_owner.presentation.foodtruck

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentRegistFoodtruckBinding
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistFoodtruckFragment : BaseFragment<FragmentRegistFoodtruckBinding>(
    FragmentRegistFoodtruckBinding::bind, R.layout.fragment_regist_foodtruck
) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.changeToolbar(
            MainToolbarControl(
                visible = true,
                title = resources.getString(R.string.regist_foodtruck_title),
                menuRes = R.menu.menu_check
            ).addMenuItemClickListener {
                showToast("등록 메뉴!")
            }.addNavIconClickListener {
                showToast("등록 뒤로가기")
            }
        )
    }
}