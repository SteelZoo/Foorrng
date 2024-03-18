package com.tasteguys.foorrng_customer.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdd.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment(
) : BaseFragment<FragmentLoginBinding>({FragmentLoginBinding.bind(it)}, R.layout.fragment_login) {
}