package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentFoodtruckInfoTabBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.menu.FoodtruckMenuFragment
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.FoodTruckInfo

class FoodtruckInfoTabFragment(
    private val foodTruckInfo: FoodTruckInfo
) : BaseFragment<FragmentFoodtruckInfoTabBinding>(
    FragmentFoodtruckInfoTabBinding::bind, R.layout.fragment_foodtruck_info_tab
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            tvTruckName.text = foodTruckInfo.name
            tvBusinessNumber.text = foodTruckInfo.businessNumber
            tvCarNumber.text = foodTruckInfo.carNumber
            tvAccountNumber.text = "1234-1234-1234"
            tvCallNumber.text = foodTruckInfo.callNumber
            tvFoodCategory.text = foodTruckInfo.category
            tvNotice.text = foodTruckInfo.notice
        }

        registerListener()
    }

    private fun registerListener(){
        binding.btnMenuManage.setOnClickListener {
            parentFragment?.let {
                it.parentFragmentManager.beginTransaction()
                    .replace(R.id.layout_main_fragment, FoodtruckMenuFragment())
                    .addToBackStack(null)
                    .commit()
            } ?: run{
                showToast("알 수 없는 오류가 발생했습니다.")
            }
        }
    }
}