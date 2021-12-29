package com.yongbeom.flo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.yongbeom.flo.R
import com.yongbeom.flo.data.Respon
import com.yongbeom.flo.databinding.ActivityMainBinding
import com.yongbeom.flo.util.RetrofitService
import com.yongbeom.flo.util.RetrofitSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private var mBinding:ActivityMainBinding? =null
    private val binding get() = mBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
         val navHostFragment:NavHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController
        setContentView(binding?.root)



        binding!!.playBox.imgPlayList.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_lyricsFragment)
        }


    }

    override fun isDestroyed(): Boolean {
        mBinding=null
        return super.isDestroyed()
    }
}