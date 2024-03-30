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
import com.tasteguys.foorrng_customer.presentation.login.DailyFavoriteViewModel
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.model.FavoriteCategory
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

    private val userViewModel:UserViewModel by activityViewModels()
    private val dailyFavoriteViewModel: DailyFavoriteViewModel by activityViewModels()


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
        dailyFavoriteViewModel.getCategory()

        binding.test.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_container, RegisterTruckFragment(isNew = true, -1))
                .addToBackStack(null)
                .commit()
        }

//        binding.test2.setOnClickListener {
////            truckViewModel.markFavoriteTruck(25)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fcv_container, RegisterFestivalFragment())
//                .addToBackStack(null)
//                .commit()
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
                        val curTruck = currentList[position]
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fcv_container, TruckInfoFragment(curTruck.truckId, curTruck.name, curTruck.type))
                            .addToBackStack(null)
                            .commit()
                    }
                })
                setOnButtonClickListener(object: TruckAdapter.TruckListHolder.ButtonClickListener{
                    override fun onToggleClick(position:Int) {

                    }

                    override fun onButtonClick(position:Int) {
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
        dailyFavoriteViewModel.getCategoryResult.observe(viewLifecycleOwner){res->
            if(res.isSuccess){
                userViewModel.foodCategory.putAll(res.getOrNull()!!.associateWith { false })
                userViewModel.getUserData()
            }
        }
        userViewModel.getUserResult.observe(viewLifecycleOwner){res->
            if(res.isSuccess){
                val category = res.getOrNull()!!.favoriteCategory
                for(food in category){
                    userViewModel.foodCategory[food] = true
                }
                favoriteAdapter.submitList(userViewModel.foodCategory.map { FavoriteCategory(it.key, it.value) })
            }
        }
    }

}