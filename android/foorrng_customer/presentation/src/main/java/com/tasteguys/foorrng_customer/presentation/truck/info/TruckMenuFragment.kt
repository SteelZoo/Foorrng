package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckMenuBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.model.mapper.toTruckMenu
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import com.tasteguys.foorrng_customer.presentation.truck.info.adapter.TruckMenuAdapter

class TruckMenuFragment(truckId: Long) : MainBaseFragment<FragmentTruckMenuBinding>(
    { FragmentTruckMenuBinding.bind(it)}, R.layout.fragment_truck_menu
) {

    private val truckViewModel: TruckViewModel by activityViewModels()

    private val truckMenuAdapter = TruckMenuAdapter(false)

    override fun setToolbar() {
        MainToolbarControl(
            true, resources.getString(R.string.menu)
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

    private fun initView(){

        binding.rvMenu.apply {
//            adapter = truckMenuAdapter.apply{
//                submitList(Dummy.truckInfo.menu)
//            }
            adapter = truckMenuAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }

        truckViewModel.truckDetailResult.observe(viewLifecycleOwner){ res->
            if(res.isSuccess){
                truckMenuAdapter.submitList(
                    res.getOrNull()?.menus!!.map { it.toTruckMenu() }
                )
            }
        }
    }
}