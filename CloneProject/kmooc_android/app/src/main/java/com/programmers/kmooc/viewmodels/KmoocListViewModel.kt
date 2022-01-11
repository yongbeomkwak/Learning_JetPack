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
        val currentLectureList = LectureList.EMPTY
        repository.next(currentLectureList) { lectureList ->
        }
    }
}

class KmoocListViewModelFactory(private val repository: KmoocRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KmoocListViewModel::class.java)) {
            return KmoocListViewModel(repository) as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}