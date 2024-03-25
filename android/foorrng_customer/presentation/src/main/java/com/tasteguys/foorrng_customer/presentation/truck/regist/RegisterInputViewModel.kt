package com.tasteguys.foorrng_customer.presentation.truck.regist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasteguys.foorrng_customer.presentation.model.FavoriteCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "RegisterInputViewModel"
@HiltViewModel
class RegisterInputViewModel @Inject constructor() : ViewModel() {

    private val _name = MutableLiveData("")
    val name: LiveData<String>
        get() = _name

    fun inputName(input: String){
        _name.postValue(input)
    }

    private val _picture = MutableLiveData("")
    val picture: LiveData<String>
        get() = _picture

    fun inputPicture(input: String){
        _picture.postValue(input)
    }

    private val _carNumber = MutableLiveData("")
    val carNumber: LiveData<String>
        get() = _carNumber

    fun inputCarNumber(input: String){
        _carNumber.postValue(input)
    }

    private val _announcement = MutableLiveData("")
    val announcement: LiveData<String>
        get() = _announcement

    fun inputAnnouncement(input: String){
        _announcement.postValue(input)
    }

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String>
        get() = _phoneNumber

    fun inputPhoneNumber(input: String){
        _phoneNumber.postValue(input)
    }

    private val _category = MutableLiveData(mutableListOf<FavoriteCategory>())
    val category: LiveData<MutableList<FavoriteCategory>>
        get() = _category

    fun setCategory(list: MutableList<FavoriteCategory>){
        _category.value = list
    }

    fun checkCategory(idx: Int){
        val isChecked = _category.value?.get(idx)?.favorite
        _category.value?.set(idx, _category.value!![idx].copy(favorite = !isChecked!!))
    }

}