package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckInfoBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.main.MainViewModel
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import com.tasteguys.foorrng_customer.presentation.truck.regist.RegisterTruckFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TruckInfoFragment"

@AndroidEntryPoint
class TruckInfoFragment(
    private val truckId: Long,
    private val truckName: String,
    private val type: String
) : MainBaseFragment<FragmentTruckInfoBinding>(
    { FragmentTruckInfoBinding.bind(it) }, R.layout.fragment_truck_info
) {
    private val truckViewModel: TruckViewModel by activityViewModels()

//    override fun setToolbar() {
//        Log.d(TAG, "setToolbar: ")
//        MainToolbarControl(
//            true, truckName, menuRes = if (type == "Foodtruck") 0 else R.menu.menu_edit
//        ).also {
//            mainViewModel.changeToolbar(it)
//        }.addNavIconClickListener {
//            parentFragmentManager.popBackStack()
//        }.addMenuItemClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fcv_container, RegisterTruckFragment(false, truckId))
//                .addToBackStack(null)
//                .commit()
//        }
//    }

    private fun changeToolbarName(name: String, type: String){
        mainViewModel.changeToolbar(
            MainToolbarControl(
                true, name, menuRes = if (type == "foodtruck") 0 else R.menu.menu_edit
            ).also {
                mainViewModel.changeToolbar(it)
            }.addNavIconClickListener {
                parentFragmentManager.popBackStack()
            }.addMenuItemClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, RegisterTruckFragment(false, truckId))
                    .addToBackStack(null)
                    .commit()
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        registerObserve()
    }

    val tabs = listOf(
        TruckBasicInfoFragment(truckId),
        TruckDetailFragment(truckId),
        TruckReviewFragment(truckId, truckName)
    )

    private fun initView() {
        truckViewModel.getTruckDetail(truckId)

        childFragmentManager.beginTransaction()
            .replace(R.id.fcv_truck_container, TruckBasicInfoFragment(truckId))
            .commit()

        binding.tblTruckInfo.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fcv_truck_container, tabs[tab!!.position])
                        .commit()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            }
        )
    }

    private fun registerObserve() {
        truckViewModel.truckDetailResult.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                val data = it.getOrNull()!!.mainData
                val picture = data.picture
                Glide.with(requireContext())
                    .load(picture)
                    .error(R.drawable.bg_profile_photo)
//                    .fitCenter()
                    .centerInside()
                    .into(binding.civTruckImg)
                changeToolbarName(data.name, it.getOrNull()!!.type)
            }
        }

    }

}