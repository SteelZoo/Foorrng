package com.tasteguys.foorrng_customer.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseActivity
import com.tasteguys.foorrng_customer.presentation.databinding.ActivityMainBinding
import com.tasteguys.foorrng_customer.presentation.profile.UserFavoriteFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity_Genseong"
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeToolbarControl()

        supportFragmentManager.beginTransaction().replace(R.id.fcv_container, UserFavoriteFragment()).commit()
    }

    private fun observeToolbarControl(){
        mainViewModel.toolbarController.observe(this){ controller ->
            with(binding.tbMain){
                if(controller.visible){
                    binding.tbMain.visibility = View.VISIBLE
                    title = controller.title
                    if(controller.menuRes!=0){
                        menu.clear()
                        inflateMenu(controller.menuRes)
                    }
                    setOnMenuItemClickListener {
                        controller.menuItemClickListener(it)
                        true
                    }
                    setNavigationOnClickListener {
                        controller.navIconClickListener()
                    }
                }
                else {
                    binding.tbMain.visibility = View.GONE
                }
            }

            binding.root.invalidate()

        }
    }
}