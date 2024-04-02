package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckBasicInfoBinding
import com.tasteguys.foorrng_customer.presentation.model.mapper.toTruckMenu
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import com.tasteguys.foorrng_customer.presentation.truck.info.adapter.TruckMenuAdapter
import com.tasteguys.foorrng_customer.presentation.truck.info.menu.TruckMenuFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.min

@AndroidEntryPoint
class TruckBasicInfoFragment(private val truckId: Long) :
    BaseFragment<FragmentTruckBasicInfoBinding>(
        { FragmentTruckBasicInfoBinding.bind(it) }, R.layout.fragment_truck_basic_info
    ) {

    private val truckViewModel: TruckViewModel by activityViewModels()
    private val truckMenuAdapter = TruckMenuAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        registerObserve()
    }

    private fun initView() {
        binding.rvMenu.apply {
            setHasFixedSize(true)
            adapter = truckMenuAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        binding.btnMoreMenu.setOnClickListener {
            requireParentFragment().parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_container, TruckMenuFragment(truckId))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun registerObserve() {
        truckViewModel.truckDetailResult.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val data = result.getOrNull()!!
                data.mainData.let {
                    with(binding) {
                        tvCallNumber.text = it.phoneNumber
                        tvCarNumber.text = it.carNumber
                        tvNotice.text = it.announcement
                        if (it.category.isNotEmpty()) {
                            tvFoodCategory.text = it.category.reduce { res, it ->
                                "$res, $it"
                            }
                        }

                        tvBusiNumber.text = it.bussiNumber
                    }

                }

                data.menus.let {
                    truckMenuAdapter.submitList(
                        it.subList(0, min(3, it.size)).map { menu -> menu.toTruckMenu() })
                }
                if(data.type == "foodtruck"){
                    binding.tvWarning.visibility = View.GONE
                }else{
                    binding.tvWarning.visibility = View.VISIBLE
                }

            }
        }
    }

}