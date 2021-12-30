package com.yongbeom.flo.Activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.yongbeom.flo.R
import com.yongbeom.flo.data.Respon
import com.yongbeom.flo.databinding.ActivityMainBinding
import com.yongbeom.flo.util.RetrofitService
import com.yongbeom.flo.util.RetrofitSet
import com.yongbeom.flo.viewModels.StatusViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private var mBinding:ActivityMainBinding? =null
    private val binding get() = mBinding
    private lateinit var mediaPlayer:MediaPlayer
    private lateinit var navController: NavController
    private val statusViewModel:StatusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
         val navHostFragment:NavHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController
        setContentView(binding?.root)
        mediaPlayer=MediaPlayer() //MediaPlayer 인스턴스 얻기


        statusViewModel.isPlay.observe(this, Observer {
            if(it) //재생 중이면  pause 아이콘 보여주기
            {
                binding!!.playBox.imgPlay.setImageResource(R.drawable.icon_pause)
            }
            else //아니면 play 아이콘 보여주
            {
                binding!!.playBox.imgPlay.setImageResource(R.drawable.icon_play)
            }
        })

        statusViewModel._isMain.observe(this, Observer {
            if(!it) //Main 회면이면
            {
                binding!!.playBox.imgPlayList.visibility= View.VISIBLE //보이게
            }
            else
            {
                binding!!.playBox.imgPlayList.visibility= View.INVISIBLE // 안보이게
           }
        })


        binding!!.playBox.imgPlay.setOnClickListener{
            statusViewModel._isPlay.value=!statusViewModel.isPlay.value!!
        }

        binding!!.playBox.imgPlayList.setOnClickListener{
            statusViewModel._isMain.value=!statusViewModel._isMain.value!!
            navController.navigate(R.id.action_mainFragment_to_lyricsFragment)
        }




    }

    override fun isDestroyed(): Boolean {
        mBinding=null
        return super.isDestroyed()
    }
}