package com.yongbeom.flo.Activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
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
import com.yongbeom.flo.util.FloApplication
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
    private var mediaPlayer:MediaPlayer? =null
    private lateinit var navController: NavController
    private val statusViewModel:StatusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
         val navHostFragment:NavHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController
        setContentView(binding?.root)
        initMedia()

        /**
          ********************** Observer ******************
         */

        statusViewModel.isPlay.observe(this, Observer {


            if(it) //재생 중이면  pause 아이콘 보여주기
            {
                binding!!.playBox.imgPlay.setImageResource(R.drawable.icon_pause)
            }
            else //아니면 play 아이콘 보여주기
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


        /**
         * ****************** Event ********************
         */



        binding!!.playBox.imgPlay.setOnClickListener{
            if (mediaPlayer!!.isPlaying)
            {
                mediaPlayer!!.pause()
            }
            else
            {
                mediaPlayer!!.start()
            }
            statusViewModel._isPlay.value=!statusViewModel.isPlay.value!!
        }

        binding!!.playBox.imgPlayList.setOnClickListener{
            statusViewModel._isMain.value=!statusViewModel._isMain.value!!
            navController.navigate(R.id.action_mainFragment_to_lyricsFragment)
        }

        binding!!.seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{

            /**
             *    onStartTrackingTouch(SeekBar seekbar) : 최초 탭하여 드래그 시작시 발생

                onStopTrackingTouch(SeekBar seekbar) : 드래그 중 발생

                onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) : 드래그 멈추면 발생

            -> 시크바 View / 변경된 값 / 사용자에 의한 변경인지(True), 코드에 의한 변경인지(False)
             * */
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                    mediaPlayer!!.seekTo(progressToMediaSeek(progress))

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })

        mediaPlayer!!.setOnCompletionListener {
            Log.e("Finish","곡 종료")
            Toast.makeText(this,"곡 종료",Toast.LENGTH_SHORT).show()
            binding!!.seekBar.progress=0
            mediaPlayer!!.seekTo(0)
            statusViewModel._isPlay.value=false
       }

    }

    fun initMedia()
    {
        mediaPlayer=MediaPlayer() //MediaPlayer 인스턴스 얻기
        mediaPlayer!!.setDataSource(FloApplication._data.file) //해당 url 파일 얻기
        mediaPlayer!!.prepare() //준비
        binding!!.seekBar.max=FloApplication._data.duration// seekBar 설정

    }


    fun progressToMediaSeek(progress:Int):Int
    {
        return progress*1000
    }


    override fun isDestroyed(): Boolean {
        mBinding=null
        if(mediaPlayer != null) {
            mediaPlayer!!.release();
            mediaPlayer = null;
        }
        return super.isDestroyed()
    }

    inner class ProgressThread():Thread()
    {
        override fun run() {
            while (statusViewModel.isPlay.value!!)
            {
                binding!!.seekBar.progress=mediaPlayer!!.currentPosition
            }
        }
    }
}