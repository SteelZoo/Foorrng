package com.tasteguys.foorrng_owner.presentation.busi_number

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentBusiNumberBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.RegistFoodtruckFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusiNumberFragment : BaseFragment<FragmentBusiNumberBinding>(
    FragmentBusiNumberBinding::bind, R.layout.fragment_busi_number
) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.changeToolbar(MainToolbarControl(visible = false))

        binding.btnSubmit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout_main_fragment,RegistFoodtruckFragment())
                .commit()
        }
    }
}