package com.yongbeom.sstviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AndroidUserViewMdoel(application: Application) :AndroidViewModel(application) {
    private var _height: MutableLiveData<Int> = MutableLiveData<Int>() //MutableLiveData는 set,get

    init {
        _height.value = 170
    }
    val height: LiveData<Int>  //LiveData는 읽기 전용(get)
        get() = _height


    fun increase()
    {
        _height.value=_height.value?.plus(1)
    }
}