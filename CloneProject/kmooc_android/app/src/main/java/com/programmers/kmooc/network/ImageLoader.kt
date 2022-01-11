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
    private val imageCache = mutableMapOf<String,Bitmap>()
    fun loadImage(url: String, completed: (Bitmap?) -> Unit) {
        if(url.isEmpty())
        {
            completed(null)
            return
        }

        if(imageCache.containsKey(url))
        {
            completed(imageCache[url])
            return
        }

        GlobalScope.launch(Dispatchers.IO) { //네트워크 작업이므로
            withContext(Dispatchers.Main){

            }

        }




    }
}