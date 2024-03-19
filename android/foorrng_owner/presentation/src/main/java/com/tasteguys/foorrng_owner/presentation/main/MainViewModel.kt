package com.tasteguys.foorrng_owner.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {
    private var _toolbarController = MutableLiveData<MainToolbarControl>()
    val toolbarController: LiveData<MainToolbarControl>
        get() = _toolbarController

    fun changeToolbar(control: MainToolbarControl){
        _toolbarController.value = control
    }
}