package com.tasteguys.foorrng_customer.presentation.truck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailData
import com.tasteguys.foorrng_customer.domain.usecase.truck.GetTruckDetailUseCase
import com.tasteguys.foorrng_customer.domain.usecase.truck.GetTruckListUseCase
import com.tasteguys.foorrng_customer.domain.usecase.truck.RegisterTruckUseCase
import com.tasteguys.foorrng_customer.domain.usecase.truck.UpdateTruckUseCase
import com.tasteguys.foorrng_customer.presentation.model.FavoriteCategory
import com.tasteguys.foorrng_customer.presentation.model.TruckDataWithAddress
import com.tasteguys.foorrng_customer.presentation.model.mapper.toTruckDataWithAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TruckViewModel @Inject constructor(
    private val registerUseCase: RegisterTruckUseCase,
    private val updateTruckUseCase: UpdateTruckUseCase,
    private val getTruckListUseCase: GetTruckListUseCase,
    private val getTruckDetailUseCase: GetTruckDetailUseCase
) : ViewModel() {

    private val _registerResult = MutableLiveData<Result<Long>>()
    val registerResult: LiveData<Result<Long>>
        get() = _registerResult

    private val _updateResult = MutableLiveData<Result<Long>>()
    val updateResult: LiveData<Result<Long>>
        get() = _updateResult

    private val _truckListResult = MutableLiveData<Result<List<TruckDataWithAddress>>>()
    val truckListResult: LiveData<Result<List<TruckDataWithAddress>>>
        get() = _truckListResult

    private val _truckDetailResult = MutableLiveData<Result<TruckDetailData>>()
    val truckDetailResult: LiveData<Result<TruckDetailData>>
        get() = _truckDetailResult

    fun registerTruck(
        name: String,
        picture: File,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ) {
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
        picture: File,
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

    fun getTruckList(
        latLeft: Double, lngLeft: Double, latRight: Double, lngRight: Double
    ) {
        viewModelScope.launch {
            getTruckListUseCase(
                latLeft, lngLeft, latRight, lngRight
            ).let { result ->
                _truckListResult.postValue(
                    result.map { list ->
                        list.map { it.toTruckDataWithAddress() }
                    }
                )
            }
        }
    }

    fun getTruckDetail(
        truckId: Long
    ) {
        viewModelScope.launch {
            getTruckDetailUseCase(truckId).let {
                _truckDetailResult.postValue(it)
            }
        }
    }


}