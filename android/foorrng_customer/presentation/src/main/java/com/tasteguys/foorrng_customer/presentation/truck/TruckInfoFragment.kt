package com.tasteguys.foorrng_customer.presentation.truck

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.base.IToolbarFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckInfoBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TruckInfoFragment"
@AndroidEntryPoint
class TruckInfoFragment : MainBaseFragment<FragmentTruckInfoBinding>(
    { FragmentTruckInfoBinding.bind(it)}, R.layout.fragment_truck_info
){
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

    val tabs = listOf(TruckBasicInfoFragment(), TruckDetailFragment(), TruckReviewFragment())

    private fun initView(){
        childFragmentManager.beginTransaction()
            .replace(R.id.fcv_truck_container, TruckBasicInfoFragment())
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