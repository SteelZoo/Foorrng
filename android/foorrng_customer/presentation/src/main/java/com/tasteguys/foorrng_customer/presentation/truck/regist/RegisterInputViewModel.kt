package com.tasteguys.foorrng_customer.presentation.truck.regist

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasteguys.foorrng_customer.presentation.base.toFile
import com.tasteguys.foorrng_customer.presentation.model.FavoriteCategory
import com.tasteguys.foorrng_customer.presentation.model.MapMark
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
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

    private val _picture = MutableLiveData<Uri?>(null)
    val picture: LiveData<Uri?>
        get() = _picture

    fun inputPicture(input: Uri){
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

    private val _foodCategory = mutableMapOf<String, Boolean>()
    val foodCategory: MutableMap<String, Boolean>
        get() = _foodCategory

    private val _category = MutableLiveData(mutableListOf<FavoriteCategory>())
    val category: LiveData<MutableList<FavoriteCategory>>
        get() = _category


    private val _markAddress = MutableLiveData("")
    val markAddress: LiveData<String>
        get() = _markAddress

    private val _markLat = MutableLiveData<Double>()
    val markLat: LiveData<Double>
        get() = _markLat

    private val _markLng = MutableLiveData<Double>()
    val markLng: LiveData<Double>
        get() = _markLng

    private val _markInfo = MutableLiveData<MapMark>()
    val markInfo: LiveData<MapMark>
        get() =_markInfo

    fun setMark(name: String, lat: Double, lng: Double){
        _markAddress.postValue(name)
        _markLat.postValue(lat)
        _markLng.postValue(lng)
        _markInfo.postValue(MapMark(name, lat, lng))
    }



    fun initData(){
        _name.value = ""
        _picture.value = null
        _carNumber.value=""
        _announcement.value=""
        _phoneNumber.value=""
        _markAddress.value=""
        _markInfo.value=MapMark("",-1.0, -1.0)
        _category.value = _category.value!!.map { FavoriteCategory(it.name, false) }.toMutableList()

    }

    var inputState: Boolean = false
    var imageChanged: Boolean = false

}