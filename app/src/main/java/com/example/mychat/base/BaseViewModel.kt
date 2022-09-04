package com.example.mychat.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N>:ViewModel() {
    var navigator:N?=null
    val messageLiveData= MutableLiveData<String>()
    val showloading= MutableLiveData<Boolean>()
}