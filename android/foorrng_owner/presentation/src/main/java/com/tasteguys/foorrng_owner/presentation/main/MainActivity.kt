package com.tasteguys.foorrng_owner.presentation

import android.os.Bundle
import com.tasteguys.foorrng_owner.presentation.R.layout
import com.tasteguys.foorrng_owner.presentation.base.BaseActivity
import com.tasteguys.foorrng_owner.presentation.base.MainToolbarControl
import com.tasteguys.foorrng_owner.presentation.busi_number.BusiNumberFragment
import com.tasteguys.foorrng_owner.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_main_fragment,BusiNumberFragment())
            .commit()


    }

    private fun setToolbar(mainToolbarControl: MainToolbarControl){

    }
}