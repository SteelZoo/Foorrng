package com.tasteguys.foorrng_owner.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.R.layout
import com.tasteguys.foorrng_owner.presentation.base.BaseActivity
import com.tasteguys.foorrng_owner.presentation.busi_number.BusiNumberFragment
import com.tasteguys.foorrng_owner.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_Genseong"
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeToolbarControl()

        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_main_fragment,BusiNumberFragment())
            .commit()
    }

    private fun observeToolbarControl(){
        mainViewModel.toolbarController.observe(this){ contoller ->
            with(binding.tbMain){
                if(contoller.visible){
                    binding.tbMain.visibility = View.VISIBLE
                    title = contoller.title
                    menu.clear()
                    inflateMenu(contoller.menuRes)
                    setOnMenuItemClickListener {
                        contoller.menuItemClickListener(it)
                        true
                    }
                    setNavigationOnClickListener {
                        contoller.navIconClickListener()
                    }
                } else {
                    binding.tbMain.visibility = View.GONE
                }
            }

            binding.root.invalidate()

        }
    }
}