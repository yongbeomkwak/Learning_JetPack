package com.yongbeom.flo.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSet {
    companion object
    {
        val baseUrl:String="https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/"
        //build를 간단히 하기 위한 함수
        fun defaultRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}