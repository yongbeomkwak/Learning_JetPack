package com.yongbeom.sstviewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel:ViewModel() {
    private var _height:MutableLiveData<Int> =MutableLiveData<Int>() //MutableLiveData는 set,get

    init {
        _height.value = 170
    }
    val height:LiveData<Int>  //LiveData는 읽기 전용(get)
    get() = _height


    fun increase()
    {
        _height.value=_height.value?.plus(1)
    }
}