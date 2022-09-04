package com.example.mychat.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<N>:ViewModel() {
    var navigator:N?=null
}