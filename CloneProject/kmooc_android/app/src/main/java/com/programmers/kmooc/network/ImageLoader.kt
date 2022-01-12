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
        if(url.isEmpty()) //url이 비어있으면 image 처리 불가
        {
            completed(null)
            return
        }

        if(imageCache.containsKey(url)) //해당 url에 해당하는 이미지가 cache에 있으면 cahce로 접근
        {
            completed(imageCache[url])
            return
        }


        //캐쉬에도 없으면 코루틴에서 네트워크 작업을 통해 가져옴

        GlobalScope.launch(Dispatchers.IO) { //네트워크 작업이므로

            try {
                val bitmap:Bitmap =BitmapFactory.decodeStream(URL(url).openStream()) //URL로 부터 Bitmap 생성 ,IO Thread에서 실행됨
                imageCache[url]=bitmap //cache에 저장

                withContext(Dispatchers.Main)
                {
                    completed(bitmap) //콜백 실행 이미지로드
                }

            } catch (e:Exception)
            {
                withContext(Dispatchers.Main)
                {
                    completed(null)
                }
            }

            withContext(Dispatchers.Main){

            }

        }




    }
}