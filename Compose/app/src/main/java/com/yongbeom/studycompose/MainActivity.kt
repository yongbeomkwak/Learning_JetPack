package com.yongbeom.studycompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yongbeom.studycompose.ui.theme.StudyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFavorite by rememberSaveable {
                //remberSaverable 약간 LiveData 같은 느낌 화면 전환을 해도 값을 기억함
                // 초기값 false
                mutableStateOf(false)
            }
            StudyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background)
                {
                    ImageCard(
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .padding(16.dp),
                        isFavorite = isFavorite
                    ) { favorite ->
                        isFavorite = favorite //갑 변경

                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onTabFavorite: (Boolean) -> Unit // 콜백함수 (매개변수 Boolean)
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
    ) {
        Box(
            modifier = Modifier.height(200.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "필수적으로 작성 해야함",
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = {
                    onTabFavorite(!isFavorite) // false -> true , 역전 true -> false
                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "좋아요",
                        tint = Color.Blue
                    )
                }
            }
        }
    }
}



