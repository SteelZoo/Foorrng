package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckBasicInfoBinding
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import com.tasteguys.foorrng_customer.presentation.truck.info.adapter.TruckMenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.min

@AndroidEntryPoint
class TruckBasicInfoFragment(private val truckId: Long) : BaseFragment<FragmentTruckBasicInfoBinding>(
    { FragmentTruckBasicInfoBinding.bind(it)}, R.layout.fragment_truck_basic_info
) {

    private val truckViewModel: TruckViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        val truckInfo = Dummy.truckInfo

        truckViewModel.truckDetailResult.observe(viewLifecycleOwner){result ->
            if(result.isSuccess){
                result.getOrNull()!!.mainData.let {
                    with(binding){
                        tvCallNumber.text = it.phoneNumber
                        tvCarNumber.text = it.carNumber
                        tvNotice.text = it.announcement
                        tvFoodCategory.text = it.category.reduce{
                            res, it -> "$res, $it"
                        }
                        tvBusiNumber.text = it.bussiNumber
                    }
                }
            }
        }

//        binding.tvBusiNumber.text = truckInfo.bussNumber
//        binding.tvCallNumber.text = truckInfo.phoneNumber
//        binding.tvCarNumber.text = truckInfo.carNumber
//        binding.tvNotice.text = truckInfo.notice
//        binding.tvFoodCategory.text = truckInfo.category.reduce{ res, it->
//            "$res, $it"
//        }

        binding.btnMoreMenu.setOnClickListener {
            requireParentFragment().parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_container, TruckMenuFragment(truckId))
                .addToBackStack(null)
                .commit()
        }

        binding.rvMenu.apply {
            adapter = TruckMenuAdapter().apply {
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