package com.tasteguys.foorrng_customer.presentation.login

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentDailyFavoriteBinding
import com.tasteguys.foorrng_customer.presentation.login.adapter.DailyFavoriteListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFavoriteFragment : BaseFragment<FragmentDailyFavoriteBinding>(
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
    }

    private fun registerObserve(){
        favoriteAdapter.submitList(mutableListOf(
            "햄버거", "아이스크림", "곱창 & 막창",
            "치킨", "디저트 & 커피", "분식",
            "케밥 & 타코", "닭꼬치", "핫도그",
            "타코야끼", "츄러스", "스테이크"
        ))
    }

}