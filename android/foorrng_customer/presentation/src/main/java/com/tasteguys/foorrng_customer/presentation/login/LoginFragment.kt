package com.tasteguys.foorrng_customer.presentation.login

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>({FragmentLoginBinding.bind(it)}, R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.buttonKakaoLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_container, DailyFavoriteFragment())
                .commit()
        }
    }
}