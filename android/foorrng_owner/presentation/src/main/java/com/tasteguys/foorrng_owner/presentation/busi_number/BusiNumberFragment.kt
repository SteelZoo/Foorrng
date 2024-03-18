package com.tasteguys.foorrng_owner.presentation.busi_number

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentBusiNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusiNumberFragment : BaseFragment<FragmentBusiNumberBinding>(
    FragmentBusiNumberBinding::bind, R.layout.fragment_busi_number
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}