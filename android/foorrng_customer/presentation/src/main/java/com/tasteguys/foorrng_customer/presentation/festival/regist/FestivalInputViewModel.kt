package com.tasteguys.foorrng_customer.presentation.festival.regist

import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasteguys.foorrng_customer.domain.usecase.festival.FestivalRegisterUpdateUseCase
import com.tasteguys.foorrng_customer.presentation.base.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FestivalInputViewModel @Inject constructor(
    private val registerUpdateUseCase: FestivalRegisterUpdateUseCase
): ViewModel() {

    var title = ""
    var organizer = ""
    var phoneNumber = ""
    var email = ""
    var talk = ""
    var infoString = ""
    var infoImg: Uri? = null
    var address = ""
    var lat = 0.0
    var lng = 0.0
    var startDate = 0L
    var endDate = 0L

    var imageChanged = false
    var inputState = false

    private val _registerResult = MutableLiveData<Result<String>>()
    val registerResult:LiveData<Result<String>>
        get() = _registerResult

    private val _updateResult = MutableLiveData<Result<String>>()
    val updateResult:LiveData<Result<String>>
        get() = _updateResult

    fun registerFestival(img: File?){
        viewModelScope.launch {
            registerUpdateUseCase(
                title = title,
                lat = lat,
                lng = lng,
                phoneNumber = phoneNumber,
                email = email,
                kakao = talk,
                organizer = organizer,
                startDate = startDate,
                endDate = endDate,
                address = address,
                mainImage = img,
            ).let {
                _registerResult.postValue(it)
            }
        }
    }

    fun updateFestival(id: Long, img: File?){
        viewModelScope.launch {
            registerUpdateUseCase(
                id = id,
                title = title,
                lat = lat,
                lng = lng,
                phoneNumber = phoneNumber,
                email = email,
                kakao = talk,
                organizer = organizer,
                startDate = startDate,
                endDate = endDate,
                address = address,
                mainImage = img,
            ).let {
                _updateResult.postValue(it)
            }
        }
    }

}