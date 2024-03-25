package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckInfoBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TruckInfoFragment"
@AndroidEntryPoint
class TruckInfoFragment(private val truckId: Long) : MainBaseFragment<FragmentTruckInfoBinding>(
    { FragmentTruckInfoBinding.bind(it)}, R.layout.fragment_truck_info
){
    private val truckViewModel: TruckViewModel by activityViewModels()

    override fun setToolbar() {
        MainToolbarControl(
            true, "야미족발"
        ).also {
            mainViewModel.changeToolbar(it)
        }.addNavIconClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    val tabs = listOf(TruckBasicInfoFragment(truckId), TruckDetailFragment(truckId), TruckReviewFragment(truckId))

    private fun initView(){
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

}