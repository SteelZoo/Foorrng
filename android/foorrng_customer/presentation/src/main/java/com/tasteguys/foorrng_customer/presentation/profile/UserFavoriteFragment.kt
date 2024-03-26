package com.tasteguys.foorrng_customer.presentation.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentUserFavoriteBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.profile.adapter.DailyFavoriteListAdapter
import com.tasteguys.foorrng_customer.presentation.profile.adapter.TruckAdapter
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import com.tasteguys.foorrng_customer.presentation.truck.info.TruckInfoFragment
import com.tasteguys.foorrng_customer.presentation.truck.regist.RegisterTruckFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "UserFavoriteFragment"
@AndroidEntryPoint
class UserFavoriteFragment : MainBaseFragment<FragmentUserFavoriteBinding>(
    {FragmentUserFavoriteBinding.bind(it)}, R.layout.fragment_user_favorite
) {

    private val favoriteAdapter = DailyFavoriteListAdapter()
    private val truckAdapter = TruckAdapter()
    private val truckViewModel: TruckViewModel by activityViewModels()

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
        registerObserve()
    }

    private fun initView(){
        truckViewModel.getFavoriteTruckList()

//        binding.test.setOnClickListener{
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fcv_container, RegisterTruckFragment())
//                .addToBackStack(null)
//                .commit()
//        }

//        binding.test2.setOnClickListener {
//            truckViewModel.markFavoriteTruck(25)
//        }

        favoriteAdapter.submitList(Dummy.category)
//        truckAdapter.submitList(Dummy.trucks)

//        truckViewModel.markFavoriteCategory.observe(viewLifecycleOwner){
//            if(it.isSuccess){
//                showToast("찜 성공")
//            }else{
//                showToast("실패")
//            }
//        }

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
                            .replace(R.id.fcv_container, TruckInfoFragment(currentList[position].truckId))
                            .addToBackStack(null)
                            .commit()
                    }
                })
                setOnButtonClickListener(object: TruckAdapter.TruckListHolder.ButtonClickListener{
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

    private fun registerObserve(){
        truckViewModel.favoriteTruckListResult.observe(viewLifecycleOwner){ res->
            if(res.isSuccess){
                res.getOrNull()?.let {
                    truckAdapter.submitList(it)
                }
            }
        }
    }

}