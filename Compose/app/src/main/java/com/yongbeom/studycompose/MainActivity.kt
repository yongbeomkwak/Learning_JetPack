package com.yongbeom.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yongbeom.studycompose.ui.theme.StudyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize() //꽉 채움
                            .background(color = Color.Blue) // 배경 색 설정
                            .padding(16.dp), //패딩 설정
                        horizontalAlignment = Alignment.CenterHorizontally,//가로 정렬 설정
                        verticalArrangement =  Arrangement.SpaceBetween,// 세로 간격 설정
                    ) {
                        Text(text = "Hello")
                        Text(text = "Word")
                    }
                }
            }
        }
    }
}


