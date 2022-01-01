package com.yongbeom.flo.util

import android.app.Application
import android.util.Log
import com.yongbeom.flo.data.Respon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FloApplication:Application() {
    private lateinit var retrofit: Retrofit
    private lateinit var service: RetrofitService
    companion object{
        lateinit var _data:Respon
    }
    override fun onCreate() {
        super.onCreate()
        BringData()

    }
    fun BringData()
    {
        retrofit=RetrofitSet.defaultRetrofit()
        service=retrofit.create(RetrofitService::class.java)
        val call: Call<Respon> = service.getInfo()
        call.enqueue(object : Callback<Respon> {
            override fun onResponse(call: Call<Respon>, response: Response<Respon>) {
                if(response.isSuccessful)
                {

                    _data= response.body()!!
                }
            }

            override fun onFailure(call: Call<Respon>, t: Throwable) {
                Log.e("Response", "Error")
            }
        })
    }
}