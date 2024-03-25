package com.tasteguys.foorrng_customer.presentation.truck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasteguys.foorrng_customer.domain.usecase.truck.RegisterTruckUseCase
import com.tasteguys.foorrng_customer.domain.usecase.truck.UpdateTruckUseCase
import com.tasteguys.foorrng_customer.presentation.model.FavoriteCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TruckViewModel @Inject constructor(
    private val registerUseCase: RegisterTruckUseCase,
    private val updateTruckUseCase: UpdateTruckUseCase
): ViewModel(){

    private val _registerResult = MutableLiveData<Result<Long>>()
    val registerResult: LiveData<Result<Long>>
        get() = _registerResult

    private val _updateResult = MutableLiveData<Result<Long>>()
    val updateResult: LiveData<Result<Long>>
        get() = _updateResult

    fun registerTruck(
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ){
        viewModelScope.launch {
            registerUseCase(
                name, picture, carNumber, announcement, phoneNumber, category
            ).let {
                _registerResult.postValue(it)
            }
        }

    }

    fun updateTruck(
        foodtruckId: Long,
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ){
        viewModelScope.launch {
            updateTruckUseCase(
                foodtruckId, name, picture, carNumber, announcement, phoneNumber, category
            ).let {
                _updateResult.postValue(it)
            }
        }

    }


}