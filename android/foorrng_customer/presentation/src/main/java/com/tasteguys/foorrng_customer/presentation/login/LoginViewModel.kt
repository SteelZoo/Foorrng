package com.tasteguys.foorrng_customer.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdd.presentation.base.PrefManager
import com.tasteguys.foorrng_customer.domain.model.user.TokenData
import com.tasteguys.foorrng_customer.domain.usecase.user.LoginUseCase
import com.tasteguys.foorrng_customer.domain.usecase.user.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject  constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val prefManager: PrefManager
) : ViewModel(){

    private val _loginResult = MutableLiveData<Result<TokenData>>()
    val loginResult: LiveData<Result<TokenData>>
        get() = _loginResult

    private val _signUpResult = MutableLiveData<Result<Long>>()

    val signUpResult: LiveData<Result<Long>>
        get() = _signUpResult

    fun login(userUid: Long, name: String, email: String){
        viewModelScope.launch {
            loginUseCase(userUid, name, email).let{
                it.getOrNull()?.let{ tk->
                    prefManager.setUserId(userUid)
                    prefManager.setAccessToken(tk.accessToken)
                    prefManager.setFcmToken(tk.refreshToken)
                }
                _loginResult.postValue(it)
            }
        }
    }

    fun signUp(userUid: Long, name: String, email: String) {
        viewModelScope.launch {
            signUpUseCase(userUid, name, email).let {
                _signUpResult.postValue(it)
            }
        }
    }
}