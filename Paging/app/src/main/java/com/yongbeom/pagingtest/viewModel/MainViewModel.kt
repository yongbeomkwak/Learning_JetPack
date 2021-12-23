package com.yongbeom.pagingtest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yongbeom.pagingtest.paging.MyPagingRepository

class MainViewModel(private val repository : MyPagingRepository) : ViewModel() {

    private val myCustomPosts2 : MutableLiveData<Int> = MutableLiveData()

    // 라이브 데이터 변경 시 다른 라이브 데이터 발행
    // Repository에서 PagingData를 가져옵니다. cachedIn(viewModelScope)를 사용하여 캐싱을 해줄 수 있습니다.
    // switchMap 리턴 타입 :LiveData<Int>
    val result = myCustomPosts2.switchMap { queryString ->
        Log.e("queryString:",queryString.toString())
        //Paging Data 설정 chacedIn
        repository.getPagingData(queryString).cachedIn(viewModelScope)
    }

    // 라이브 데이터 변경 하여 observer가 감지하게 함
    fun searchPost(userId: Int) {
        Log.e("SearchPost:",userId.toString())
        myCustomPosts2.value = userId
    }
}