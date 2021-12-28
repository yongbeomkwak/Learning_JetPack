package com.yongbeom.flo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.yongbeom.flo.R
import com.yongbeom.flo.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private var mBinding: ActivitySplashBinding? =null
    private val binding get() = mBinding
    private val imgUrl:String = "https://grepp-cloudfront.s3.ap-northeast-2.amazonaws.com/programmers_imgs/competition-imgs/2020-Flo-challenge/FLO_Splash-Img3x(1242x2688).png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Glide.with(this).load(imgUrl).into(binding?.imgSplash!!)
        startLoading()


    }

    private fun startLoading() { //핸들러 사용하여,3초 동안 로딩
        var handler= Handler()
        handler.postDelayed( Runnable {
            intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            //3초후 이동
            finish()},2000)


    }
}