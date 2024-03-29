package com.tasteguys.foorrng_customer.presentation.truck.regist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailMarkData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckRegisterUpdateData
import com.tasteguys.foorrng_customer.domain.usecase.truck.RegisterTruckUseCase
import com.tasteguys.foorrng_customer.domain.usecase.truck.UpdateTruckUseCase
import com.tasteguys.foorrng_customer.presentation.model.TruckOperationInfo
import com.tasteguys.foorrng_customer.presentation.model.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TruckRegisterUpdateViewModel @Inject constructor(
    private val registerUseCase: RegisterTruckUseCase,
    private val updateTruckUseCase: UpdateTruckUseCase,
): ViewModel(){
    private val _registerResult = MutableLiveData<Result<TruckRegisterUpdateData>>()
    val registerResult: LiveData<Result<TruckRegisterUpdateData>>
        get() = _registerResult

    private val _updateResult = MutableLiveData<Result<TruckRegisterUpdateData>>()
    val updateResult: LiveData<Result<TruckRegisterUpdateData>>
        get() = _updateResult

    private val _markRegisterResult = MutableLiveData<Result<TruckDetailMarkData>>()
    val markRegisterResult: LiveData<Result<TruckDetailMarkData>>
        get()=_markRegisterResult


    fun registerTruck(
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ) {
        viewModelScope.launch {
            registerUseCase(
                name, picture, carNumber, announcement, phoneNumber, category
            ).let { res->
                _registerResult.postValue(res)
            }
        }
    }

    fun registerOperationInfo(
        id: Long,
        address: String,
        lat: Double,
        lng: Double,
        operationInfo: List<TruckOperationInfo>,
    ){
        viewModelScope.launch {
            registerUseCase(
                id, address, lat, lng, operationInfo.map { it.toDomain() }
            ).let{
                _markRegisterResult.postValue(it)
            }
        }
    }

    fun updateTruck(
        foodtruckId: Long,
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ) {
        viewModelScope.launch {
            updateTruckUseCase(
                foodtruckId, name, picture, carNumber, announcement, phoneNumber, category
            ).let {
                _updateResult.postValue(it)
            }
        }
    }

}