package com.yongbeom.flo.util

import com.yongbeom.flo.data.Respon
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("song.json")
    fun getInfo():Call<Respon>


}