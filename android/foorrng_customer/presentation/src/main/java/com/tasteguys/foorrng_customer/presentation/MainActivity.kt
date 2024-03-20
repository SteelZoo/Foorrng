package com.tasteguys.foorrng_customer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tasteguys.foorrng_customer.presentation.base.BaseActivity
import com.tasteguys.foorrng_customer.presentation.databinding.ActivityMainBinding
import com.tasteguys.foorrng_customer.presentation.login.LoginFragment
import com.tasteguys.foorrng_customer.presentation.profile.UserFavoriteFragment

class MainActivity : BaseActivity<ActivityMainBinding>({ActivityMainBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.fcv_container, UserFavoriteFragment()).commit()
    }
}