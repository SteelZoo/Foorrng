package com.tasteguys.foorrng_owner.presentation.busi_number

import android.content.Context
import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentBusiNumberBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.RegistFoodtruckFragment
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusiNumberFragment : MainBaseFragment<FragmentBusiNumberBinding>(
    FragmentBusiNumberBinding::bind, R.layout.fragment_busi_number
) {
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout_main_fragment, RegistFoodtruckFragment())
                .commit()
        }
    }
}