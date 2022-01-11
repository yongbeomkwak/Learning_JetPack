package com.programmers.kmooc.models

import java.io.Serializable

data class LectureList(
    val count: Int,
    val numPages: Int,
    val previous: String,
    val next: String,
    var lectures: List<Lecture>
) : Serializable {
    companion object {
        val EMPTY = LectureList(0, 0, "", "", emptyList())
    }
}
