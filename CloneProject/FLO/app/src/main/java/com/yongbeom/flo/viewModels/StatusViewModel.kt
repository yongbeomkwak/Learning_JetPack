package com.yongbeom.flo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusViewModel:ViewModel() {
    public val _isPlay:MutableLiveData<Boolean> = MutableLiveData(false)
    public val isPlay:LiveData<Boolean> = _isPlay
    public val _isMain:MutableLiveData<Boolean> = MutableLiveData(true)
    public val isMain:LiveData<Boolean> = _isMain
    public val _pos:MutableLiveData<Int> = MutableLiveData(0)
    public val pos:LiveData<Int> = _pos
    public val _isSeek:MutableLiveData<Boolean> = MutableLiveData(false)
    public val isSeek:LiveData<Boolean> = _isSeek



}