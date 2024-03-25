package com.tasteguys.foorrng_owner.presentation.location.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.tasteguys.foorrng_owner.presentation.model.location.RecommendLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationRecommendViewModel @Inject constructor(

) : ViewModel() {
    private var _recommendLocationList = MutableLiveData<Result<List<RecommendLocation>>>()
    val recommendLocationList: LiveData<Result<List<RecommendLocation>>>
        get() = _recommendLocationList

    fun getRecommendLocationList() {
        viewModelScope.launch {
            _recommendLocationList.postValue(
                Result.success(
                    dummyRecommendLocationList()
                )
            )
        }
    }

    private fun dummyRecommendLocationList(): List<RecommendLocation>{
        return mutableListOf<RecommendLocation>().apply {
            for (i in 0..10) {
                add(
                    RecommendLocation(
                        address = "대구광역시 중구 명륜로23길 80",
                        comment = "근처에 유사 업종의 가게가 적습니다.",
                        latLng = LatLng(35.863585+(i/1000.0), 128.595737+(i/1000.0))
                    )
                )
            }
        }
    }

}