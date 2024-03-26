package com.tasteguys.foorrng_customer.presentation.login

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.kakao.sdk.user.UserApiClient
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentLoginBinding
import com.tasteguys.retrofit_adapter.FoorrngException
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "LoginFragment"
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>({FragmentLoginBinding.bind(it)}, R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
    }

    private fun initView(){
        binding.buttonKakaoLogin.setOnClickListener {
            kakaologin{ uId, name, email ->
                loginViewModel.login(uId, name, email)
            }
        }
    }

    private fun setObserver(){
        loginViewModel.loginResult.observe(viewLifecycleOwner){
            if(it.isSuccess){
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, DailyFavoriteFragment())
                    .commit()
            }else{
                it.exceptionOrNull()?.let{t->
                    if(t is FoorrngException && t.code == FoorrngException.NOT_EXIST_USER){
                        showSignUpDialog()
                    }else{
                        showSnackBar("로그인에 실패했습니다. :(")
                    }
                }
            }
        }

        loginViewModel.signUpResult.observe(viewLifecycleOwner){
            if(it.isSuccess){
                showSnackBar("회원가입이 완료됐습니다. :)")
            }else{
                showSnackBar(it.exceptionOrNull()?.message?: "회원가입에 실패했습니다. :(")
            }
        }
    }

    private fun showSignUpDialog() {
        AlertDialog.Builder(_activity)
            .setTitle("회원가입")
            .setMessage("존재하지 않는 회원 입니다.\n회원가입을 진행하시겠습니까?")
            .setPositiveButton("확인") { _, _ ->
                kakaologin { uId, name, email ->
                    loginViewModel.signUp(uId, name, email)
                }
            }
            .setNegativeButton("취소") { _, _ -> }
            .show()
    }

    private fun kakaologin(callback: (uId: Long, name: String, email: String) -> (Unit)) {
        UserApiClient.instance.loginWithKakaoTalk(_activity) { token, error ->
            if (error != null) {
                showSnackBar("카카오 로그인 실패")
            } else if (token != null) {
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        showSnackBar("카카오 회원정보 호출 실패")
                    } else if (user != null) {
                        with(user){
                            val uId = id
                            val name = kakaoAccount?.profile?.nickname
                            val email = kakaoAccount?.email
//                            Log.d(TAG, "kakaologin: uid: $uId, name: $name, email:$email ")
                            if (uId != null && name != null && email != null) {
                                callback(uId, name, email)
                            }
                        }
                    }
                }
            }
        }
    }
}