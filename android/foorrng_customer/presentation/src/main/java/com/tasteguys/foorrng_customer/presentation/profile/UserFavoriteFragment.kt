package com.tasteguys.foorrng_customer.presentation.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.base.IToolbarFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentUserFavoriteBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.main.MainViewModel
import com.tasteguys.foorrng_customer.presentation.profile.adapter.DailyFavoriteListAdapter
import com.tasteguys.foorrng_customer.presentation.profile.adapter.FavoriteTruckAdapter
import com.tasteguys.foorrng_customer.presentation.truck.TruckInfoFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "UserFavoriteFragment"
@AndroidEntryPoint
class UserFavoriteFragment : MainBaseFragment<FragmentUserFavoriteBinding>(
    {FragmentUserFavoriteBinding.bind(it)}, R.layout.fragment_user_favorite
) {

    private val favoriteAdapter = DailyFavoriteListAdapter()
    private val truckAdapter = FavoriteTruckAdapter()
    override fun setToolbar() {
        MainToolbarControl(
            true, resources.getString(R.string.my_favorite_info)
        ).also {
            mainViewModel.changeToolbar(it)
        }
    }

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
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fcv_container, TruckInfoFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                })
                setOnButtonClickListener(object: FavoriteTruckAdapter.TruckListHolder.ButtonClickListener{
                    override fun onToggleClick(isChecked: Boolean) {

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