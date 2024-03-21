package com.tasteguys.foorrng_customer.presentation.truck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckMenuBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.truck.adapter.TruckMenuAdapter

class TruckMenuFragment : MainBaseFragment<FragmentTruckMenuBinding>(
    { FragmentTruckMenuBinding.bind(it)}, R.layout.fragment_truck_menu
) {

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
        binding.rvMenu.apply {
            adapter = TruckMenuAdapter(isSimple = false).apply{
                submitList(Dummy.truckInfo.menu)
            }
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

}