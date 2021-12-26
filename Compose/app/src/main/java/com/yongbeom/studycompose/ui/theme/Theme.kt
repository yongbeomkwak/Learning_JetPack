package com.yongbeom.studycompose.ui.theme

import android.graphics.Color
import android.graphics.Color.WHITE
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White

//다크모드
private val DarkColorPalette = darkColors(
    surface = Blue,
    onSurface = Navy,
    primary = Navy,
    onPrimary = Chartreuse
)

//주간모
private val LightColorPalette = lightColors(
    surface = Blue,
    onSurface = White,
    primary = LightBlue,
    onPrimary = Navy
)


@Composable
fun StudyComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) { //만약 다크모드면
        DarkColorPalette //다크
    } else {
        LightColorPalette
    }


    MaterialTheme( //실질적용 적용 코드
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}