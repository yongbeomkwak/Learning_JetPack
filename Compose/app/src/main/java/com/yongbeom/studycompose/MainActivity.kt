package com.yongbeom.studycompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           setContent {
               HomeScreen(MainViewModelT())
           }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModelT)
{
    // 총 4가지 방법으로 State 사용가능
    val (text:String,setText:(String)->Unit) = remember{  //by를 사용하지 않으면 함수를 이용한다
        //TextField에서 자주 사용
        mutableStateOf("Hello World")
    }

    val text1:MutableState<String> = remember { // .value로 접근
        mutableStateOf("Hello World")
    }

    var text2:String  by  remember {  // by를 이용하면 getter와 setter를 그냥 변수로 처리함 (델리게이트 이용)
        mutableStateOf("Hello World")
    }

    val text3:State<String> =viewModel.liveData.observeAsState("Hello World")
    
    Column() {
        Text(text = "Hello World")
        Button(onClick = {
            text1.value="변경1"
            Log.e("OUTPUT",text1.value)
            text2="변경2"
            Log.e("OUTPUT",text2)
            setText("변경")
            Log.e("OUTPUT",text)
            viewModel.changeValue("변경4")
            Log.e("OUTPUT",viewModel.data.value)
            viewModel.changeLiveData("HHHHH")
            Log.e("OUTPUT",text3.value)

        }) {
            Text(text = "클릭")
        }
        TextField(value = text, onValueChange =setText)
    }
}

class MainViewModelT:ViewModel()
{
    private val _data:MutableState<String> = mutableStateOf("Hello World")
    val data:State<String> =_data

    private val _liveData=MutableLiveData<String>()
    val liveData:LiveData<String> =_liveData

    fun changeValue(value:String)
    {
        _data.value=value
    }

    fun changeLiveData(value: String)
    {
        _liveData.value=value
    }

}