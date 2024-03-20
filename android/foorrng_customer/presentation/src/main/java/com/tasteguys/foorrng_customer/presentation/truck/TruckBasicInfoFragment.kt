package com.tasteguys.foorrng_customer.presentation.truck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckBasicInfoBinding
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckInfoBinding
import com.tasteguys.foorrng_customer.presentation.truck.adapter.TruckMenuAdapter
import kotlin.math.min

class TruckBasicInfoFragment : BaseFragment<FragmentTruckBasicInfoBinding>(
    { FragmentTruckBasicInfoBinding.bind(it)}, R.layout.fragment_truck_basic_info
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        val truckInfo = Dummy.truckInfo
        binding.tvBusiNumber.text = truckInfo.bussNumber
        binding.tvCallNumber.text = truckInfo.phoneNumber
        binding.tvCarNumber.text = truckInfo.carNumber
        binding.tvNotice.text = truckInfo.notice
        binding.tvFoodCategory.text = truckInfo.category.reduce{ res, it->
            "$res, $it"
        }

        binding.rvMenu.apply {
            adapter = TruckMenuAdapter(isSimple = true).apply {
                submitList(truckInfo.menu.subList(0, min(3, truckInfo.menu.size)))
            }
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

}