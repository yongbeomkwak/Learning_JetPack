package com.yongbeom.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             val viewModel by viewModels<MainViewModel>() //뷰 모델 정의
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text=viewModel.data.value, fontSize = 30.sp)
                Button(onClick = {
                    viewModel.changeValue()
                }) {
                    Text(text = "변경")
                }

            }
        }
    }
}

class MainViewModel : ViewModel() {
    private val _data = mutableStateOf("Hello") // 데이터는 private
    val data:State<String> =_data //외부로는 이렇게 제공

    fun changeValue() //변경 메소드
    {
        _data.value="World"
    }
}