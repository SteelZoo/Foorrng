package com.tasteguys.foorrng_owner.presentation.location.regist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasteguys.foorrng_owner.presentation.model.run.RunDay
import com.tasteguys.foorrng_owner.presentation.model.run.RunLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationRegistViewModel @Inject constructor(

) : ViewModel() {
    private var _runLocation = MutableLiveData<RunLocation>()
    val runLocation: LiveData<RunLocation>
        get() = _runLocation

    private var _runDayList = MutableLiveData<List<RunDay>>()
    val runDayList: LiveData<List<RunDay>>
        get() = _runDayList

    fun setRunLocation(runLocation: RunLocation) {
        _runLocation.value = runLocation
    }

    fun addRunDay(runDay: RunDay){
        val list = _runDayList.value?.toMutableList() ?: mutableListOf()
        list.add(runDay)
        _runDayList.value = list
    }

    fun deleteRunDay(runDay: RunDay){
        val list = _runDayList.value?.toMutableList() ?: mutableListOf()
        list.remove(runDay)
        _runDayList.value = list
    }

}