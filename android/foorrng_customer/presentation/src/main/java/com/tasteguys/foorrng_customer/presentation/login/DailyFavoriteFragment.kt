package com.tasteguys.foorrng_customer.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentDailyFavoriteBinding
import com.tasteguys.foorrng_customer.presentation.main.MainActivity
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.profile.adapter.DailyFavoriteListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFavoriteFragment : MainBaseFragment<FragmentDailyFavoriteBinding>(
    { FragmentDailyFavoriteBinding.bind(it)}, R.layout.fragment_daily_favorite
) {
    private val favoriteAdapter = DailyFavoriteListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        registerObserve()
    }

    private fun initView(){
        binding.rvFavoriteCategory.apply{
            adapter = favoriteAdapter.apply {
                setOnItemClickListener(object : BaseHolder.ItemClickListener{
                    override fun onClick(position: Int) {

                    }

                })
            }
            layoutManager = FlexboxLayoutManager(context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
            }
        }

        binding.btnConfirm.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }
    }

    private fun registerObserve(){
        favoriteAdapter.submitList(Dummy.category)
    }

}