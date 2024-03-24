package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckDetailBinding
import com.tasteguys.foorrng_customer.presentation.truck.info.adapter.TruckMarkInfoAdapter

class TruckDetailFragment : BaseFragment<FragmentTruckDetailBinding>(
    { FragmentTruckDetailBinding.bind(it)}, R.layout.fragment_truck_detail
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.rvOpInfo.apply {
            adapter = TruckMarkInfoAdapter().apply {
                submitList(Dummy.markInfo)
            }
        }
    }

}