package com.programmers.kmooc.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.URL

object ImageLoader {
    fun loadImage(url: String, completed: (Bitmap?) -> Unit) {
        //TODO: String -> Bitmap 을 구현하세요
    }
}