package com.tasteguys.foorrng_owner.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,R.layout.fragment_login
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}