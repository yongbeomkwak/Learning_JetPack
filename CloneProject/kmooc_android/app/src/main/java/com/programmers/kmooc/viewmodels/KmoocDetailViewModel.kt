package com.programmers.kmooc.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programmers.kmooc.repositories.KmoocRepository


class KmoocDetailViewModel(private val repository: KmoocRepository) : ViewModel() {

    fun detail(courseId: String) {
        repository.detail(courseId) { lecture ->
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