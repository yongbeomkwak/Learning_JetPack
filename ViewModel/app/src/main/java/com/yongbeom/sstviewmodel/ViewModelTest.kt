package com.yongbeom.sstviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yongbeom.sstviewmodel.databinding.ActivityViewModelTestBinding

class ViewModelTest : AppCompatActivity() {
    private lateinit var binding: ActivityViewModelTestBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_view_model_test)
        //뷰 모델은 뷰모델 프로바이더(this(owner)).get(ViewModel::class.java)

        /**
         * ViewModelProvider를 사용할 때 this를 넘겨주는 데 이는 owner를 의미한다.
        ViewModelStore를 누가 소유하고 있느냐? -> this가 소유하고 있다 = MainActivity가 소유하고 있다.
        그렇다면 ViewModelStore은 무엇일까?
        ViewModelStore은 ViewModel 객체가 HashMap 구조로 저장되는 곳이다.
        즉, get() 안에 있는 'UserViewModel..'은 객체를 찾아오기 위한 Key값으로 쓰이는 것이다.
         * */
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        binding.user=userViewModel //바인딩


        //Observer 설정
        userViewModel.height.observe(this, Observer {
            binding.textViewHeight.text = it.toString()
        })

    }
}