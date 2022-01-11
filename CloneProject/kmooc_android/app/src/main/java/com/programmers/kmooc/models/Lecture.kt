package com.programmers.kmooc.models

import java.io.Serializable
import java.util.*

data class Lecture(
    val id: String,                 // 아이디
    val number: String,             // 강좌번호
    val name: String,               // 강좌명
    val classfyName: String,        // 강좌분류
    val middleClassfyName: String,  // 강좌분류2
    val courseImage: String,        // 강좌 썸네일 (media>image>small)
    val courseImageLarge: String,   // 강좌 이미지 (media>image>large)
    val shortDescription: String,   // 짧은 설명
    val orgName: String,            // 운영기관
    val start: Date,                // 운영기간 시작
    val end: Date,                  // 운영기간 종료
    val teachers: String?,          // 교수진
    val overview: String?           // 상제정보(html)
) : Serializable {
    companion object {
        val EMPTY = Lecture(
            "", "", "", "", "",
            "", "", "", "", Date(), Date(), null, null
        )
    }
}