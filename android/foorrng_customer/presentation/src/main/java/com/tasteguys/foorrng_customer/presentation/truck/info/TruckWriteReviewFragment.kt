package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckWriteReviewBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.model.mapper.ReviewMap
import com.tasteguys.foorrng_customer.presentation.truck.info.adapter.TruckReviewBtnAdapter

class TruckWriteReviewFragment(private val truckId: Long, private val truckName: String) : MainBaseFragment<FragmentTruckWriteReviewBinding>(
    { FragmentTruckWriteReviewBinding.bind(it)}, R.layout.fragment_truck_write_review
)  {

    override fun setToolbar() {
        MainToolbarControl(
            true, truckName
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
        with(binding){
            rvReviewBtn.apply {
                adapter = TruckReviewBtnAdapter().apply {
                    submitList(ReviewMap.reviewMap.keys.toList())
                }
            }
        }
    }

}