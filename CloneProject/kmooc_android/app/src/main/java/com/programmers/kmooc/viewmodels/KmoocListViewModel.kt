package com.programmers.kmooc.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programmers.kmooc.models.LectureList
import com.programmers.kmooc.repositories.KmoocRepository
import java.util.Collections.addAll

class KmoocListViewModel(private val repository: KmoocRepository) : ViewModel() {

    var progressVisible=MutableLiveData<Boolean>()
    var lectureList=MutableLiveData<LectureList>()

    fun list() {
        progressVisible.postValue(true) //progressBar 보여주고
        repository.list { lectureList ->
            this.lectureList.postValue(lectureList) //리스트 repository에서 가져오기
            progressVisible.postValue(false) //progressBar 꺼주고
        }
    }

    fun next() {
        progressVisible.postValue(true) // 로딩중 켜기
        val currentLectureList=this.lectureList.value ?: return //현재 리스트 가져오기
        repository.next(currentLectureList) { lectureList ->
            val currentLectures=currentLectureList.lectures //현재 강좌들
            val mergedLectures=currentLectures.toMutableList() //합칠 강좌들
                .apply {
                    addAll(lectureList.lectures) //추가
                }
            lectureList.lectures=mergedLectures
            this.lectureList.postValue(lectureList) //라이브데이터 변경
           progressVisible.postValue(false) //로딩중 끄기
        }
    }
}

class KmoocListViewModelFactory(private val repository: KmoocRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KmoocListViewModel::class.java)) { // .isAssignableFrom
            return KmoocListViewModel(repository) as T //해당 타입의 viewModel 리턴
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}