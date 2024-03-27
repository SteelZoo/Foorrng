package com.tasteguys.foorrng_customer.presentation.festival

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FestivalViewModel @Inject constructor(

) : ViewModel(){

    private val _address = MutableLiveData<String>("")
    val address: LiveData<String>
        get() = _address

    fun setAddress(address: String){
        _address.postValue(address)
    }

    private val _tempAddress = MutableLiveData<String>("")
    val tempAddress: LiveData<String>
        get() = _tempAddress

    fun setTempAddress(address: String){
        _tempAddress.postValue(address)
    }

}