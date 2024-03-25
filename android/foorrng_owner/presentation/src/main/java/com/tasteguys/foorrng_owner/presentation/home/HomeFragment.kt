package com.tasteguys.foorrng_owner.presentation.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.base.PermissionHelper
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentHomeBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.info.FoodtruckInfoFragment
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment

class HomeFragment : MainBaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind, R.layout.fragment_home
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.onBackPressedDispatcher.addCallback(mainActivity.onBackPressedCallback)

        checkPermission()

        binding.cvMyFoodtruck.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout_main_fragment, FoodtruckInfoFragment())
                .addToBackStack(null)
                .commit()
        }
    }


    private fun checkPermission() {
        val permissionList =
            mutableListOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
            ).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    add(Manifest.permission.POST_NOTIFICATIONS)
                    add(Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }

        if (PermissionHelper.checkPermissionList(_activity,permissionList).isNotEmpty()) {
            PermissionHelper.requestPermissionList_fragment(this, permissionList.toTypedArray(),
                deniedListener = {
                    showPermissionDialog()
                })
        }
    }
    private fun showPermissionDialog(){
        MaterialAlertDialogBuilder(_activity)
            .setTitle("푸르릉과 함께하려면 다음의 권한이 필요해요")
            .setMessage("- 정확한 위치\n- 갤러리 접근\n- 카메라\n\n위의 권한이 없으면 많은 기능들을 이용하지 못합니다. 설정으로 이동할까요?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("확인") { _, _ ->
                startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${_activity.packageName}")
                    ).apply {
                        addCategory(Intent.CATEGORY_DEFAULT)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }
            .show()
    }

}