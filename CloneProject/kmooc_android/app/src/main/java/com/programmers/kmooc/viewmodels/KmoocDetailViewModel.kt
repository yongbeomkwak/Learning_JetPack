package com.programmers.kmooc.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.repositories.KmoocRepository


class KmoocDetailViewModel(private val repository: KmoocRepository) : ViewModel() {

    var progressVisible = MutableLiveData<Boolean>()
    val lecture = MutableLiveData<Lecture>()

    fun detail(courseId: String) {
        progressVisible.postValue(true)
        repository.detail(courseId) { lecture -> //repository에서 끝난 lecture를 라이브 데이터에 할당함
            this.lecture.postValue(lecture)
            progressVisible.postValue(false)

        }
    }
}

class KmoocDetailViewModelFactory(private val repository: KmoocRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KmoocDetailViewModel::class.java)) {
            return KmoocDetailViewModel(repository) as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}