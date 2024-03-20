package com.tasteguys.foorrng_customer.presentation.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentUserFavoriteBinding
import com.tasteguys.foorrng_customer.presentation.profile.adapter.DailyFavoriteListAdapter
import com.tasteguys.foorrng_customer.presentation.profile.adapter.FavoriteTruckAdapter

private const val TAG = "UserFavoriteFragment"
class UserFavoriteFragment : BaseFragment<FragmentUserFavoriteBinding>(
    {FragmentUserFavoriteBinding.bind(it)}, R.layout.fragment_user_favorite
) {

    private val favoriteAdapter = DailyFavoriteListAdapter()
    private val truckAdapter = FavoriteTruckAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initView()

    }

    private fun initView(){
        favoriteAdapter.submitList(Dummy.category)
        truckAdapter.submitList(Dummy.trucks)
    }

    private fun initAdapter(){
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

        binding.rvFavoriteTruck.apply{
            adapter = truckAdapter.apply {
                setOnItemClickListener(object : BaseHolder.ItemClickListener{
                    override fun onClick(position: Int) {

                    }
                })
                setOnButtonClickListener(object: FavoriteTruckAdapter.TruckListHolder.ButtonClickListener{
                    override fun onToggleClick(isChecked: Boolean) {
                        Log.d(TAG, "onToggleClick: $isChecked")
                        showToast(isChecked.toString())
                    }

                    override fun onButtonClick() {
                    }

                })
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
            }
        }

    }

}