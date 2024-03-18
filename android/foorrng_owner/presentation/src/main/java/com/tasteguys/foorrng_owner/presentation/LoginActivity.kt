package com.tasteguys.foorrng_owner.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tasteguys.foorrng_owner.presentation.base.BaseActivity
import com.tasteguys.foorrng_owner.presentation.busi_number.BusiNumberFragment
import com.tasteguys.foorrng_owner.presentation.databinding.ActivityLoginBinding
import com.tasteguys.foorrng_owner.presentation.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    ActivityLoginBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_login_fragment,LoginFragment())
            .commit()
    }
}