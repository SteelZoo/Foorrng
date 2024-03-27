package com.tasteguys.foorrng_customer.presentation.festival.regist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentRegisterFestivalBinding
import com.tasteguys.foorrng_customer.presentation.festival.FestivalViewModel
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RegisterFestivalFragmen"
@AndroidEntryPoint
class RegisterFestivalFragment : MainBaseFragment<FragmentRegisterFestivalBinding>(
    { FragmentRegisterFestivalBinding.bind(it)}, R.layout.fragment_register_festival
) {

    private val festivalViewModel: FestivalViewModel by activityViewModels()

    override fun setToolbar() {
        MainToolbarControl(
            true, resources.getString(R.string.register_festival)
        ).also {
            mainViewModel.changeToolbar(it)
        }.addNavIconClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        registerObserve()
    }

    private fun initView(){
        with(binding){
            tiLocation.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, FestivalSelectLocationFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun registerObserve(){
        festivalViewModel.address.observe(viewLifecycleOwner){
            binding.tilLocation.editText!!.setText(it)
        }
    }

}