package com.tasteguys.foorrng_owner.presentation.foodtruck.regist

import androidx.lifecycle.ViewModel
import com.tasteguys.foorrng_owner.domain.usecase.foodtruck.RegistFoodtruckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistFoodtruckViewModel @Inject constructor(
    private val registFoodtruckUseCase: RegistFoodtruckUseCase
) : ViewModel() {

}