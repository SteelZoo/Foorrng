package com.tasteguys.foorrng_customer.presentation.festival.regist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentRegisterFestivalBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl

class RegisterFestivalFragment : MainBaseFragment<FragmentRegisterFestivalBinding>(
    { FragmentRegisterFestivalBinding.bind(it)}, R.layout.fragment_register_festival
) {

    override fun setToolbar() {
        MainToolbarControl(
            true, resources.getString(R.string.register_festival)
        ).also {
            mainViewModel.changeToolbar(it)
        }
    }

}