package com.tasteguys.foorrng_customer.presentation.truck.regist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.base.GalleryLauncher
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentRegisterTruckBinding
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.profile.adapter.DailyFavoriteListAdapter
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.log

private const val TAG = "RegisterTruckFragment"

@AndroidEntryPoint
class RegisterTruckFragment : MainBaseFragment<FragmentRegisterTruckBinding>(
    { FragmentRegisterTruckBinding.bind(it) }, R.layout.fragment_register_truck
) {

    private val truckViewModel: TruckViewModel by activityViewModels()
    private val registerInputViewModel: RegisterInputViewModel by activityViewModels()
    private val favoriteListAdapter = DailyFavoriteListAdapter()

    private val galleryLauncher: GalleryLauncher by lazy{
        GalleryLauncher(this)
    }


    override fun setToolbar() {
        MainToolbarControl(
            visible = true,
            title = resources.getString(R.string.register_foodtruck),
            menuRes = R.menu.menu_check
        ).addMenuItemClickListener {
            confirmDialog()
//            showToast("등록 완료")
        }.addNavIconClickListener {
            checkBackStackDialog()
        }.also {
            mainViewModel.changeToolbar(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        galleryLauncher.pictureCallbackListener = object : GalleryLauncher.PictureCallbackListener{
            override fun onGetData(data: Uri) {
                Glide.with(requireContext())
                    .load(data)
                    .fallback(R.drawable.logo_truck)
                    .into(binding.ivTruckPhoto)
                registerInputViewModel.inputPicture(data, requireContext())
            }
        }
        registerObserve()
    }

    private fun initView() {

        if (registerInputViewModel.category.value!!.isEmpty()) {
            registerInputViewModel.setCategory(Dummy.category)
            favoriteListAdapter.submitList(registerInputViewModel.category.value)
        }

        with(binding) {
            rvMenuCategory.apply {
                layoutManager = FlexboxLayoutManager(context)
                adapter = favoriteListAdapter.apply {
                    submitList(registerInputViewModel.category.value)
                    setOnItemClickListener(object : BaseHolder.ItemClickListener {
                        override fun onClick(position: Int) {
                            registerInputViewModel.checkCategory(position)
                        }
                    })
                }
            }

            tilTruckName.editText!!.addTextChangedListener {
                registerInputViewModel.inputName(it.toString())
            }
            tilCallNumber.editText!!.addTextChangedListener {
                registerInputViewModel.inputPhoneNumber(it.toString())
            }
            tilCarNumber.editText!!.addTextChangedListener {
                registerInputViewModel.inputCarNumber(it.toString())
            }
            tilNotice.editText!!.addTextChangedListener {
                registerInputViewModel.inputAnnouncement(it.toString())
            }

            tiLocation.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, TruckSelectLocationFragment())
                    .addToBackStack(null)
                    .commit()
            }

            with(registerInputViewModel) {
                tilTruckName.editText!!.setText(name.value)
                tilCallNumber.editText!!.setText(phoneNumber.value)
                tilCarNumber.editText!!.setText(carNumber.value)
                tilNotice.editText!!.setText(announcement.value)
                tilLocation.editText!!.setText(markAddress.value)
            }

            ivTruckPhoto.setOnClickListener {
                galleryLauncher.openGallery()
            }
        }


    }

    private fun registerObserve(){
        registerInputViewModel.markAddress.observe(viewLifecycleOwner){
            binding.tilLocation.editText!!.setText(it)
        }


        truckViewModel.registerResult.observe(viewLifecycleOwner){
            Log.d(TAG, "initViewResult: ${it.isSuccess}")
            if(it.isSuccess){
                registerOperationInfo(it.getOrNull()!!.id)
            }
        }

        truckViewModel.markRegisterResult.observe(viewLifecycleOwner){
            if(it.isSuccess){
                showToast("등록 성공")
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun register() {
        with(registerInputViewModel) {
            truckViewModel.registerTruck(
                name.value!!,
                picture.value!!,
                carNumber.value!!,
                announcement.value!!,
                phoneNumber.value!!,
                category.value!!.filter { it.favorite }.map { it.name }
            )
        }
    }

    private fun registerOperationInfo(id: Long){
        with(registerInputViewModel) {
            truckViewModel.registerOperationInfo(
                id,
                markAddress.value!!,
                markLat.value!!,
                markLng.value!!,
                listOf()
            )
        }
    }


    private fun checkBackStackDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("등록을 취소하시겠습니까?")
            .setMessage("작성 중인 내용이 모두 삭제됩니다.")
            .setPositiveButton(resources.getString(R.string.btn_confirm)) { _, _ ->
                parentFragmentManager.popBackStack()
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun confirmDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("제보 하시겠습니까?")
            .setMessage("제보시 3회이상의 신고가 있을 시에만 삭제가 가능합니다.")
            .setPositiveButton(resources.getString(R.string.btn_confirm)) { _, _ ->
                register()

            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}