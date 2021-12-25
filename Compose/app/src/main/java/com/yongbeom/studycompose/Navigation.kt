package com.yongbeom.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Navigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController, //컨트롤러 설정
                startDestination = "first" // 첫 화면
            )
            {
                composable("first") //composable 함수를 이용하여 route 명과 함께 안에 화면 내용을 작성
                {
                    FirstScreen(naviController = navController)
                }
                composable("second") //composable 함수를 이용하여 route 명과 함께 안에 화면 내용을 작성
                {
                    SecondScreen(naviController = navController)
                }
                composable("third/{value}") //third 이동시 key:value를 같이 이동
                { backStackEntry ->
                    //backStackEntry 변수로 넘겨주는 변수 접근 가는  .arguments
                    ThirdScreen(
                        naviController = navController,
                        value=backStackEntry.arguments?.getString("value") ?: "" //key value값으로 value를 넘겨줌 단 null 일 경우 공백
                    )
                }
            }
        }
    }
}

@Composable
fun FirstScreen(naviController: NavController) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "첫 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            naviController.navigate("second") // 이동
        }) {
            Text(text = "두 번째 화면")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if (value.isNotEmpty()) {
                naviController.navigate("third/$value") //third로  이동과 동시에 value값을 넘김
            }
        }) {
            Text(text = "세 번째 화면")
        }
    }
}

@Composable
fun SecondScreen(naviController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "두 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            naviController.navigateUp() //뒤로가기
        }) {

            Text(text = "뒤로가")
        }


    }
}

@Composable
fun ThirdScreen(naviController: NavController, value: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "세 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = value)
        Button(onClick = {
            naviController.navigateUp()
        }) {
            Text(text = "뒤로가")
        }


    }
}